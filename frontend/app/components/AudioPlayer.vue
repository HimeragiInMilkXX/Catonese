<script setup lang="ts">

    import { ref, useAudio, watch } from '#imports';
    import { Play, Pause, FastForward, Volume, Volume1, Volume2, VolumeOff } from 'lucide-vue-next';
    import { SliderRange, SliderRoot, SliderThumb, SliderTrack } from 'reka-ui'
    import { Button } from './ui/button';

    const { audioSource, imageSource } = defineProps<{ audioSource: string, imageSource: string }>();

    const audio = useAudio( audioSource );

    const audioTime = ref<number[]>([audio.currentTime.value]);
    const audioVolume = ref<number[]>([audio.volume.value]);
    const isTimeDragged = ref<boolean>(false);

    defineExpose( { isTimeDragged, returnState: () => isTimeDragged.value = false, duration: audio.duration } );

    const emits = defineEmits(['nextLine'])

    const handleTimelineDrag = () => {

        audio.pause();
        audio.setTime( audioTime.value[0] ?? 0 );

        isTimeDragged.value = true;

    }

    const handleVolumeDrag = () => {

        audio.setVolume( audioVolume.value[0] ?? 1 );

    }

    const formatTime = ( time: number | undefined ) => {

        if( isNaN( time ) || !time ) return "00:00";

        const minute = Math.floor( time / 60 ).toString();
        const seconds = Math.floor( time % 60 ).toString();

        return `${minute.padStart( 2, "0" )}:${seconds.padStart( 2, "0" )}`

    }

    watch( audio.currentTime, ( currentTime ) => {

        if( audio.isPlaying ) {
            audioTime.value[0] = currentTime;
            emits( 'nextLine', currentTime );
        }

    })

</script>

<template>
    
    <div>

        <img :src="imageSource" alt="" class="aspect-square object-cover">

        <div class="w-full flex flex-col items-center box-border p-3 gap-1">

            <div class="flex gap-5">

                <FastForward style="transform: rotate(180deg)" class="fill-gray-400" @click="() => audio.fastBackward(5)"/>

                <Pause v-if="audio.isPlaying.value" class="fill-gray-400" @click="() => audio.pause()"/>
                <Play v-else class="fill-gray-400" @click="() => audio.play()"/>

                <FastForward class="fill-gray-400" @click="() => audio.fastForward(5)"/>

            </div>

            <!-- TIMELINE -->
            <div class="flex gap-2 w-full items-center">

                <p class=""> {{ formatTime(audioTime[0]) }} </p>

                <SliderRoot
                    v-model="audioTime"
                    class="relative flex items-center select-none touch-none grow h-5"
                    :default-value="[0]"
                    :max="audio.duration.value"
                    :step="0.01"
                    orientation="horizontal"
                    @update:model-value="handleTimelineDrag"
                    @pointerup="() => audio.play()"
                >
                    <SliderTrack class="bg-stone-500/30 relative grow rounded-full h-2">
                        <SliderRange class="absolute bg-red-300 rounded-full h-full" />
                    </SliderTrack>
                    <SliderThumb
                        class="block opacity-0 bg-red-300 rounded-full"
                        aria-label="Timeline"
                    />
                </SliderRoot>

                <p> {{ formatTime(audio.duration.value) }} </p>

            </div>

            <!-- VOLUME -->
            <div class="flex gap-2 w-full items-center">

                <Volume2 v-if="audio.volume.value >= 0.66"/>
                <Volume1 v-else-if="audio.volume.value >= 0.33"/>
                <Volume v-else-if="audio.volume.value > 0"/>
                <VolumeOff v-else-if="audio.volume.value == 0"/>

                <SliderRoot
                    v-model="audioVolume"
                    class="relative flex items-center select-none touch-none w-full h-5"
                    :default-value="[0]"
                    :max="1"
                    :step="0.01"
                    orientation="horizontal"
                    @update:model-value="handleVolumeDrag"
                >
                    <SliderTrack class="bg-stone-500/30 relative grow rounded-full h-2">
                        <SliderRange class="absolute bg-blue-300 rounded-full h-full" />
                    </SliderTrack>
                    <SliderThumb
                        class="block opacity-0 bg-blue-300 rounded-full"
                        aria-label="Volume"
                    />
                </SliderRoot>

            </div>

        </div>

    </div>

</template>

<style scoped>

</style>