<script setup lang="ts">

    import { computed, ref, setPartComplete, useRoute, watch } from '#imports';
    import Flashcard from '~/components/practice/flashcard.vue';
    import { useFlashcard } from '~/composables/practice/useFlashcard';
    import { Button } from '~/components/ui/button';
    import { Check, X } from 'lucide-vue-next';
    import { SliderRange, SliderRoot, SliderThumb, SliderTrack } from 'reka-ui'

    const route = useRoute();
    const id = route.params.id;

    const flashcardRef = ref<typeof Flashcard>();

    const { pairs, currentQuestion, next, isFinished, isConfident, order } = useFlashcard(id);

    const remainingPairs = computed( () => [pairs.value?.length - order.value.length])

    watch( isFinished, async () => {

        const res = await setPartComplete( "flashcard" );
        console.log( res );

    })

</script>

<template>
    
    <section class="relative m-auto box-border h-fit gap-6 flex flex-col max-[683px]:mt-[15vh] max-[683px]:justify-center bg-amber-gold-50 min-[683px]:p-12 p-6 rounded-2xl border border-amber-gold-100 shadow shadow-amber-gold-100">

        <h2 class="text-left opacity-30 absolute -top-6 left-0"> Lesson {{ id }} - Flashcard </h2>
        
        <PracticeFlashcard v-if="currentQuestion && !isFinished" :front="currentQuestion?.cantonese" :back="currentQuestion?.translation" @next="() => next()"
            @setConfident="( confident ) => isConfident = confident" ref="flashcardRef"/>

        <div class="flex gap-3" v-if="currentQuestion && !isFinished">

            <Button @click.stop="() => flashcardRef?.nextCard(true)" variant="secondary" class="grow p-6 bg-green-200 hover:bg-green-300 border border-green-200 shadow shadow-green-200"> <Check :size="30" :stroke-width="2.5"/> </Button>
            <Button @click.stop="() => flashcardRef?.nextCard(false)" variant="secondary" class="grow p-6 bg-red-200 hover:bg-red-300 border border-red-200 shadow shadow-red-200"> <X :size="30" :stroke-width="2.5"/> </Button>

        </div>

        <div class="flex gap-3 w-full items-center" v-if="currentQuestion && !isFinished">

            <p style="font-size: 0.85rem;" class="leading-tight"> {{ pairs?.length - order.length }}</p>

            <SliderRoot
                v-model="remainingPairs"
                class="relative flex items-center select-none touch-none pointer-events-none grow h-5  transition-all"
                :default-value="[0]"
                :max="pairs?.length"
                orientation="horizontal"
            >
                <SliderTrack class="bg-stone-500/30 relative grow rounded-full h-2 transition-all">
                    <SliderRange class="absolute bg-cerulean-100 rounded-full h-full  transition-all" />
                </SliderTrack>
                <SliderThumb
                    class="block opacity-0 bg-cerulean-100 rounded-full"
                    aria-label="Timeline "
                />
            </SliderRoot>

            <p style="font-size: 0.85rem;" class="leading-tight"> {{ pairs?.length }}</p>

        </div>

        <div v-if="isFinished" class="p-6 gap-3 h-full text-4xl font-bold text-center flex flex-col items-center">

            <p> Nice Job! </p>
            <Button @click="() => navigateTo(`/lessons/${id}`)" variant="secondary" class="w-fit p-6 px-9 text-2xl relative border-none bg-amber-gold-300 hover:bg-amber-gold-400"> Close </Button>
        
        </div>
    </section> 
</template>