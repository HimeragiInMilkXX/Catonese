<script setup lang="ts">

    import { ref, useRecorder } from '#imports';
    import { Button } from './ui/button';
    import { Mic } from 'lucide-vue-next';
    import { Input } from './ui/input';

    const { isRecording, audioUrl, audioBlob, duration, startRecording, stopRecording } = useRecorder();

    const formatTime = ( time: number | undefined ) => {

        if( isNaN( time ) || !time ) return "00:00";

        const minute = Math.floor( time / 60 ).toString();
        const seconds = Math.floor( time % 60 ).toString();

        return `${minute.padStart( 2, "0" )}:${seconds.padStart( 2, "0" )}`

    }

    const emits = defineEmits(['upload'])

    const callUpload = () => {

        setTimeout( () => {

            if( !audioBlob.value ) {

                alert( "Oops! There seems to be a problem in recording, please try again later" )
                return;

            }

            emits( 'upload', audioBlob.value );

        }, 0 );

    }

    const { isUploading } = defineProps<{ isUploading: boolean }>();

</script>

<template>
    
    <div :class="!isUploading ? 'pointer-events-auto opacity-100' : 'pointer-events-none opacity-50'">

        <div class="flex flex-col items-center gap-6">

            <Button class="h-fit w-fit p-9 rounded-full relative border-none bg-amber-gold-300 hover:bg-amber-gold-400" variant="secondary" :class="isRecording && 'recording'"
                @click="() => {
                    if( isRecording ) {
                        stopRecording();
                        callUpload();
                    } else { startRecording(); }
                }"> <Mic :size="100" class="stroke-cerulean-900"/> </Button>

            <p class="text-5xl"> {{ formatTime( duration ) }} </p>

        </div>

    </div>

</template>

<style scoped>

    .recording::after {

        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        border-radius: 200px;
        border: 2px solid var(--color-amber-gold-500);
        box-sizing: border-box;
        z-index: 50;
        box-shadow: 0px 0px 5px var(--color-amber-gold-200);

        animation-name: recording;
        animation-duration: 0.85s;
        animation-iteration-count: infinite;
        animation-timing-function: linear;

    }

    @keyframes recording {

        0% {

            transform: scale(1);

        }

        100% {

            transform: scale(1.3);

        }

    }

</style>