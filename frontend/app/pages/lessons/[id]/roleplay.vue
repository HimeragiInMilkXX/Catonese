<script setup lang="ts">

    import { setPartComplete, useRoute, useRuntimeConfig } from '#imports';
    import { ref, watch } from 'vue';
    import AudioRecorder from '~/components/AudioRecorder.vue';
    import Roleplay from '~/components/practice/roleplay.vue';
    import { useRoleplay } from '~/composables/practice/useRoleplay';
    import type { AlignmentResult } from '~/types/types';

    const isUploading = ref<boolean>(false);

    const uploadEndpoint = `${useRuntimeConfig().public.apiBase}/alignment/align`;

    const upload = ( audioBlob: Blob ) => {

        const formData = new FormData();
        formData.append( 'audio', audioBlob, 'recording.webm' );
        formData.append( 'text', removePunctuation(currentQuestion.value?.answer) );

        isUploading.value = true;

        $fetch<{ data: { alignmentResult: AlignmentResult }, success: boolean }>( uploadEndpoint, {

            method: "POST",
            body: formData

        } )
        .then( res => {

            console.log( res.data.alignmentResult )
            isUploading.value = false;

            roleplayRef.value.score = Math.round(handleScore(
                res.data.alignmentResult.confidence_stats.mean, res.data.alignmentResult.transcription ))

        } )
        .catch( err => {console.log( err ); isUploading.value = false} );

    }

    const route = useRoute();
    const id: string = route.params.id as string;

    const { isFinished, finalScore, next, handleScore, currentQuestion, isAnswering, removePunctuation } = useRoleplay( id );

    const roleplayRef = ref();

    watch( isFinished, async () => {

        const res = await setPartComplete( "roleplay" );
        console.log( res );

    })

</script>

<template>

    <Roleplay ref="roleplayRef" :lesson="id" :is-answering="isAnswering" :current-question="currentQuestion" @next="() => next()" :final-score="finalScore" :is-finished="isFinished">

        <AudioRecorder @upload="( audioBlob ) => upload( audioBlob )" :is-uploading="isUploading" v-show="isAnswering"/>

    </Roleplay>

</template>

<style scoped>

</style>