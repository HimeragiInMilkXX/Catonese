export const useRecorder = () => {

    const isRecording = ref<boolean>(false);
    const audioBlob = ref<Blob | null>();
    const audioUrl = ref<string | null>("");
    const duration = ref<number>(0);

    const streamRef = ref<MediaStream>();
    const mediaRecorderRef = ref<MediaRecorder>();
    const audioChunks = ref<Blob[]>([]);

    const timer = ref<number>(0);

    const startRecording = async () => {

        clearRecording();

        try {

            const stream = await navigator.mediaDevices.getUserMedia({

                audio: {

                    channelCount: 1,
                    sampleRate: 48000,
                    echoCancellation: true,
                    noiseSuppression: true,

                }

            });

            streamRef.value = stream;

            const mediaRecorder = new MediaRecorder( stream, {

                mimeType: 'audio/webm;codecs=opus',
                audioBitsPerSecond: 128000,

            })

            mediaRecorderRef.value = mediaRecorder;
            audioChunks.value = [];

            mediaRecorder.ondataavailable = ( e ) => {

                if( e.data.size > 0 ) audioChunks.value.push( e.data );

            }

            mediaRecorder.onstop = () => {

                const blob = new Blob( audioChunks.value, { type: 'audio/webm' } );
                audioBlob.value = blob;
                const url = URL.createObjectURL( blob );
                audioUrl.value = url;

                if( streamRef.value ) streamRef.value.getTracks().forEach( track => track.stop() );

            }

            mediaRecorder.start();
            isRecording.value = true;
            duration.value = 0;

            timer.value = setInterval( () => duration.value += 1, 1000)

        } catch( err ) {

            alert( "Could not access microphone. Please check permissions" );

        }

    }

    const stopRecording = () => {

        if( mediaRecorderRef.value && isRecording.value ) {

            mediaRecorderRef.value.stop();
            isRecording.value = false;

            if( timer.value ) clearInterval( timer.value );

        }

    }

    const clearRecording = () => {

        if( !audioUrl.value ) return;

        if( audioUrl )
            URL.revokeObjectURL( audioUrl.value );

        audioBlob.value = null;
        audioUrl.value = null;
        duration.value = 0

    }

    return { isRecording, audioUrl, audioBlob, duration, timer, startRecording, stopRecording, clearRecording }

}