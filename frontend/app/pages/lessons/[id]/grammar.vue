<script setup lang="ts">

    import { setPartComplete, useNuxtApp, useRoute, watch } from '#imports';
    import { useGrammar } from '~/composables/practice/useGrammar';
    import { Button } from '~/components/ui/button';
    import { useGrammarExercise } from '~/composables/practice/useGrammarExercise';

    const route = useRoute();
    const id = route.params.id;

    const { next, isFinished, getCurrentGrammar, grammar, allowNext, getBlanksIndex } = useGrammar(id);

    watch( isFinished, async () => {

        const res = await setPartComplete( "grammar" );
        console.log( res );

    })

</script>

<template>

    <div v-if="!isFinished" class="max-[639px]:mb-12! min-[683px]:max-w-2/3 max-[683px]:w-[70vw] m-auto box-border flex justify-center h-[70vh] max-[1002px]:h-fit gap-6 max-[1002px]:flex max-[1002px]:flex-col max-[1002px]:mt-30 relative max-[1002px]:mb-12!">

        <p class="absolute left-12 top-12 opacity-30 z-10"> Lesson {{ id }} - Grammar </p>
        <p class="absolute right-12 top-12 opacity-30 z-10 max-[1002px]:hidden"> Practice </p>

        <div v-if="!isFinished && grammar" class="bg-amber-50 p-12 rounded-2xl border border-amber-100 shadow shadow-amber-100 flex justify-center items-center relative basis-1/2">

            <PracticeGrammarLearning :grammar="getCurrentGrammar()?.grammar" :explanation="getCurrentGrammar()?.explanation" :examples="getCurrentGrammar()?.examples"/>

        </div>

        <div v-if="!isFinished && grammar" class="bg-amber-50 p-12 rounded-2xl border border-amber-100 shadow shadow-amber-100 flex flex-col justify-center items-center relative basis-1/2">

            <PracticeGrammarExercising :blank-positions="getBlanksIndex()" :grammar="getCurrentGrammar()?.grammar" :exercises="getCurrentGrammar()?.exercises" :key="getCurrentGrammar()?.grammar" @exercise-completed="() => { allowNext = true }"/>
            <Button class="mt-5 w-2/3 text-2xl px-12 py-6 bg-cerulean-100 border-cerulean-100 text-cerulean-700! hover:bg-cerulean-200" @click="next" v-show="allowNext"> Next </Button>

        </div>

    </div>

    <section v-else class="relative m-auto box-border h-fit gap-6 w-150 flex flex-col max-[639px]:mx-6 max-[639px]:w-auto bg-amber-gold-50 p-12 rounded-2xl border border-amber-gold-100 shadow shadow-amber-gold-100">
        <div class="p-6 gap-3 h-full text-4xl font-bold text-center flex flex-col items-center">

            <p> Nice Job! </p>
            <Button @click="() => navigateTo(`/lessons/${id}`)" variant="secondary" class="w-fit p-6 px-9 text-2xl relative border-none bg-amber-gold-300 hover:bg-amber-gold-400"> Close </Button>
        
        </div>
    </section>

</template>

<style scoped>

</style>