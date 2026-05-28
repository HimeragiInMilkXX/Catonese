<script setup lang="ts">

    import type { RoleplayQuestion } from '~/types/types';
    import { Button } from '../ui/button';
    import { ref } from '#imports';

    const { lesson, isAnswering, currentQuestion, isFinished, finalScore } = defineProps<{ lesson: string, isAnswering: boolean, currentQuestion: RoleplayQuestion | undefined, isFinished: boolean, finalScore: NumberFieldRootEmits }>();

    const emits = defineEmits(['next']);

    const score = ref<number>();

    defineExpose( { score } );

    function processAnswer(): string | undefined {

        let processedText = currentQuestion?.answer;

        if( !currentQuestion?.ignore ) return processedText;

        currentQuestion?.ignore.forEach( ( ignore: [ number, number, string ]  ) => {

            const startFrom: number = ignore[0];
            const replaceBy: string = ignore[2];

            const placeholderLength: number = replaceBy.length;

            const firstPart: string = processedText?.slice( 0, startFrom );
            const secondPart: string = processedText?.slice( startFrom + placeholderLength );

            processedText = `${firstPart}～${secondPart}`

        })

        return processedText;

    }

    function getComment() {

        if( finalScore >= 90 ) return "Damn man you already mastered it!"
        else if( finalScore >= 70 ) return "Good job, you are so close!"
        else if( finalScore >= 50 ) return "Nice try, consolidate more!"
        else if( finalScore >= 30 ) return "You need more practice!"
        else return "Not even close man"

    }

</script>

<template>
    <div class="max-w-2/3 m-auto box-border flex justify-center h-[70vh] gap-6 max-[1002px]:flex max-[1002px]:flex-col max-[1002px]:mt-30">

        <div class="bg-amber-50 p-12 rounded-2xl border border-amber-100 shadow shadow-amber-100 flex justify-center items-center relative basis-1/2">
            <p class="absolute left-12 top-12 opacity-30"> Lesson {{ lesson }} - Roleplay </p>
            <div v-if="isAnswering" class="text-[2.5vw] flex justify-center leading-snug text-center font-medium max-[1002px]:mt-12 min-[1002px]:max-h-2/3 min-[1002px]:overflow-y-scroll max-h-[20vh] overflow-y-scroll max-[1002px]:text-sm" style="scrollbar-width: none;">
                {{ currentQuestion?.question }}
            </div>
            <div v-else-if="isFinished" class="text-5xl flex flex-col items-center justify-center leading-snug text-center font-medium w-[58vw] gap-6 max-[1002px]:pt-12">
                <div class="flex flex-col gap-3">
                    <p class="leading-tight">{{ getComment() }}</p>
                    <p class="leading-tight">{{ Math.round(finalScore) }}</p>
                </div>
                <Button @click="() => navigateTo(`/lessons/${lesson}`)" variant="secondary" class="w-fit p-6 px-9 text-2xl relative border-none bg-amber-gold-300 hover:bg-amber-gold-400"> Close </Button>
            </div>
            <div v-else class="text-5xl flex flex-col items-center justify-center leading-snug text-center font-medium w-[58vw] gap-6 max-[1002px]:pt-12">
                <div class="flex flex-col gap-3">
                    <p class="leading-tight">{{ processAnswer() }}</p>
                    <p class="leading-tight">{{ score }}</p>
                </div>
                <Button @click="() => emits('next')" variant="secondary" class="w-fit p-6 px-9 text-2xl relative border-none bg-amber-gold-300 hover:bg-amber-gold-400"> Next </Button>
            </div>
        </div>

        <div class="bg-amber-50 p-12 rounded-2xl border border-amber-100 shadow shadow-amber-100 flex justify-center items-center relative basis-1/2" v-show="isAnswering"><slot/></div>
    </div>
</template>

<style scoped>

</style>