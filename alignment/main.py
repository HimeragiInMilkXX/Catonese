from fastapi import FastAPI, File, UploadFile, Form, HTTPException
from fastapi.responses import JSONResponse
import torch
import torchaudio
from transformers import Wav2Vec2ForCTC, Wav2Vec2Processor
import numpy as np
from typing import List, Dict
import tempfile
import os
import soundfile as sf
import subprocess

app = FastAPI(title="Cantonese Forced Alignment API")

# Global model variables
model = None
processor = None
device = None

def convert_to_wav_with_ffmpeg(input_path: str, output_path: str):
    """Convert any audio/video file to WAV using ffmpeg"""
    try:
        subprocess.run([
            'ffmpeg', '-i', input_path,
            '-vn',  # No video
            '-acodec', 'pcm_s16le',  # PCM 16-bit
            '-ar', '16000',  # 16kHz sample rate
            '-ac', '1',  # Mono
            '-y',  # Overwrite output
            output_path
        ], check=True, capture_output=True)
        return True
    except subprocess.CalledProcessError as e:
        raise Exception(f"FFmpeg conversion failed: {e.stderr.decode()}")
    except FileNotFoundError:
        raise Exception("FFmpeg not found. Please install ffmpeg.")

def load_model():
    """Load the Cantonese wav2vec2 model"""
    global model, processor, device
    
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    print(f"Using device: {device}")
    
    # Using a Cantonese-capable model
    model_name = "ctl/wav2vec2-large-xlsr-cantonese"
    
    print("Loading model and processor...")
    processor = Wav2Vec2Processor.from_pretrained(model_name)
    model = Wav2Vec2ForCTC.from_pretrained(model_name)
    model = model.to(device)
    model.eval()
    print("Model loaded successfully!")

@app.on_event("startup")
async def startup_event():
    """Load model on startup"""
    load_model()

@app.get("/")
async def root():
    return {
        "message": "Cantonese Forced Alignment API",
        "status": "ready",
        "device": str(device)
    }

@app.post("/align")
async def align_audio(
    audio: UploadFile = File(...),
    text: str = Form(...)
):
    """
    Perform forced alignment on Cantonese audio
    
    Args:
        audio: Audio file (WAV, WEBM, MP3, etc.)
        text: Cantonese text transcription
    
    Returns:
        JSON with word-level timestamps
    """
    try:
        # Determine file extension from uploaded file
        file_ext = os.path.splitext(audio.filename)[1] if audio.filename else '.webm'
        
        # Save uploaded file temporarily
        with tempfile.NamedTemporaryFile(delete=False, suffix=file_ext) as tmp_file:
            content = await audio.read()
            tmp_file.write(content)
            tmp_path = tmp_file.name
        
        # Convert to WAV if needed (for WebM or other formats)
        wav_path = tmp_path
        needs_conversion = file_ext.lower() in ['.webm', '.mp4', '.mkv', '.avi']
        
        if needs_conversion:
            wav_path = tmp_path.replace(file_ext, '.wav')
            convert_to_wav_with_ffmpeg(tmp_path, wav_path)
        
        # Load and resample audio using soundfile
        waveform, sample_rate = sf.read(wav_path)
        
        # Convert to tensor and add channel dimension if mono
        waveform = torch.FloatTensor(waveform)
        if waveform.dim() == 1:
            waveform = waveform.unsqueeze(0)
        else:
            waveform = waveform.T  # soundfile returns [samples, channels]
        
        # Convert to mono if stereo
        if waveform.shape[0] > 1:
            waveform = torch.mean(waveform, dim=0, keepdim=True)
        
        # Resample to 16kHz if needed
        if sample_rate != 16000:
            resampler = torchaudio.transforms.Resample(sample_rate, 16000)
            waveform = resampler(waveform)
        
        # Process audio
        input_values = processor(
            waveform.squeeze().numpy(),
            sampling_rate=16000,
            return_tensors="pt"
        ).input_values
        
        input_values = input_values.to(device)
        
        # Get model outputs
        with torch.no_grad():
            logits = model(input_values).logits
        
        # Get predicted tokens
        predicted_ids = torch.argmax(logits, dim=-1)
        
        # Decode to get transcription
        transcription = processor.batch_decode(predicted_ids)[0]
        
        # Get alignment with confidence scores
        alignment = get_alignment(
            logits[0].cpu().numpy(),
            text,
            processor,
            return_confidence=True
        )
        
        # Clean up temp files
        os.unlink(tmp_path)
        if needs_conversion and os.path.exists(wav_path):
            os.unlink(wav_path)
        
        return JSONResponse(content={
            "status": "success",
            "transcription": transcription,
            "reference_text": text,
            "alignment": alignment,
            "audio_duration": waveform.shape[1] / 16000,
            "confidence_stats": {
                "mean": round(np.mean([a["confidence"] for a in alignment]), 4),
                "min": round(min([a["confidence"] for a in alignment]), 4),
                "max": round(max([a["confidence"] for a in alignment]), 4)
            }
        })
        
    except Exception as e:
        # Clean up temp files on error
        if 'tmp_path' in locals():
            try:
                os.unlink(tmp_path)
            except:
                pass
        if 'wav_path' in locals() and 'needs_conversion' in locals() and needs_conversion:
            try:
                os.unlink(wav_path)
            except:
                pass
        raise HTTPException(status_code=500, detail=f"Error processing audio: {str(e)}")

def get_alignment(
    logits: np.ndarray,
    text: str,
    processor,
    return_confidence: bool = False
) -> List[Dict]:
    """
    Perform CTC-based forced alignment
    
    Args:
        logits: Model output logits [time, vocab]
        text: Reference text
        processor: Wav2Vec2 processor
        return_confidence: Whether to include confidence scores
    
    Returns:
        List of alignment dictionaries with timestamps and optional confidence
    """
    # Get frame-level predictions and probabilities
    probs = np.exp(logits) / np.sum(np.exp(logits), axis=-1, keepdims=True)  # Softmax
    predicted_ids = np.argmax(logits, axis=-1)
    
    # Time per frame (assume 20ms per frame for wav2vec2)
    time_per_frame = 0.02
    
    # Decode predictions
    tokens = processor.tokenizer.convert_ids_to_tokens(predicted_ids)
    
    # Remove special tokens and blanks
    cleaned_tokens = []
    token_timestamps = []
    token_confidences = []
    
    for i, (token, token_id) in enumerate(zip(tokens, predicted_ids)):
        if token not in ['<pad>', '<s>', '</s>', '|']:
            cleaned_tokens.append(token)
            token_timestamps.append(i * time_per_frame)
            if return_confidence:
                token_confidences.append(float(probs[i, token_id]))
    
    # Merge consecutive duplicate tokens (CTC collapse)
    merged_tokens = []
    merged_timestamps = []
    merged_confidences = []
    
    prev_token = None
    for i, token in enumerate(cleaned_tokens):
        timestamp = token_timestamps[i]
        confidence = token_confidences[i] if return_confidence else None
        
        if token != prev_token:
            merged_tokens.append(token)
            merged_timestamps.append(timestamp)
            if return_confidence:
                merged_confidences.append(confidence)
            prev_token = token
        elif return_confidence and merged_confidences:
            # Average confidence for merged tokens
            merged_confidences[-1] = (merged_confidences[-1] + confidence) / 2
    
    # Create character-level alignment
    alignment = []
    for i, (token, start_time) in enumerate(zip(merged_tokens, merged_timestamps)):
        end_time = merged_timestamps[i + 1] if i + 1 < len(merged_timestamps) else (len(logits) * time_per_frame)
        
        align_item = {
            "token": token,
            "start": round(start_time, 3),
            "end": round(end_time, 3)
        }
        
        if return_confidence:
            align_item["confidence"] = round(merged_confidences[i], 4)
        
        alignment.append(align_item)
    
    return alignment

@app.post("/transcribe")
async def transcribe_audio(audio: UploadFile = File(...)):
    """
    Transcribe Cantonese audio without alignment
    
    Args:
        audio: Audio file (WAV, WEBM, MP3, etc.)
    
    Returns:
        JSON with transcription
    """
    try:
        # Determine file extension from uploaded file
        file_ext = os.path.splitext(audio.filename)[1] if audio.filename else '.webm'
        
        # Save uploaded file temporarily
        with tempfile.NamedTemporaryFile(delete=False, suffix=file_ext) as tmp_file:
            content = await audio.read()
            tmp_file.write(content)
            tmp_path = tmp_file.name
        
        # Convert to WAV if needed (for WebM or other formats)
        wav_path = tmp_path
        needs_conversion = file_ext.lower() in ['.webm', '.mp4', '.mkv', '.avi']
        
        if needs_conversion:
            wav_path = tmp_path.replace(file_ext, '.wav')
            convert_to_wav_with_ffmpeg(tmp_path, wav_path)
        
        # Load and resample audio using soundfile
        waveform, sample_rate = sf.read(wav_path)
        
        # Convert to tensor and add channel dimension if mono
        waveform = torch.FloatTensor(waveform)
        if waveform.dim() == 1:
            waveform = waveform.unsqueeze(0)
        else:
            waveform = waveform.T  # soundfile returns [samples, channels]
        
        # Convert to mono if stereo
        if waveform.shape[0] > 1:
            waveform = torch.mean(waveform, dim=0, keepdim=True)
        
        # Resample to 16kHz if needed
        if sample_rate != 16000:
            resampler = torchaudio.transforms.Resample(sample_rate, 16000)
            waveform = resampler(waveform)
        
        # Process audio
        input_values = processor(
            waveform.squeeze().numpy(),
            sampling_rate=16000,
            return_tensors="pt"
        ).input_values
        
        input_values = input_values.to(device)
        
        # Get model outputs
        with torch.no_grad():
            logits = model(input_values).logits
        
        # Get predicted tokens
        predicted_ids = torch.argmax(logits, dim=-1)
        
        # Decode to get transcription
        transcription = processor.batch_decode(predicted_ids)[0]
        
        # Clean up temp files
        os.unlink(tmp_path)
        if needs_conversion and os.path.exists(wav_path):
            os.unlink(wav_path)
        
        return JSONResponse(content={
            "status": "success",
            "transcription": transcription,
            "audio_duration": waveform.shape[1] / 16000
        })
        
    except Exception as e:
        if 'tmp_path' in locals():
            try:
                os.unlink(tmp_path)
            except:
                pass
        if 'wav_path' in locals() and 'needs_conversion' in locals() and needs_conversion:
            try:
                os.unlink(wav_path)
            except:
                pass
        raise HTTPException(status_code=500, detail=f"Error processing audio: {str(e)}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)