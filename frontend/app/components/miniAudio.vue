<script setup lang="ts">

    import { onMounted, ref, useAudio, watch } from '#imports';
    import { Volume2 } from 'lucide-vue-next';

    const { audioSource, size, strokeWidth } = defineProps<{ audioSource: string, size?: number, strokeWidth?: number }>();

    const audio = useAudio( audioSource );

    const play = () => audio.play();

    defineExpose( { play } );

</script>

<template>
    
    <div @click.stop="() => play()">
        <Volume2 :size="size" :stroke-width="strokeWidth"/>
    </div>

</template>