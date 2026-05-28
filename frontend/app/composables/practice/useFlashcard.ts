import type { Flashcard, FlashcardPair } from "~/types/types";

export const useFlashcard = ( lesson: string ) => {

    const pairs = ref<[[string, string]]>();

    const currentFront = ref<string>();
    const currentBack = ref<string>();

    const length = ref<number>();
    const order = ref<number[]>([]);

    const currentQuestion = ref<FlashcardPair>();
    const currentIndex = ref<number>();
    const isFinished = ref<boolean>(false);

    const isConfident = ref<boolean>(true);

    const { getJson } = useStorage();

    $fetch<{data: Flashcard, success: boolean}>( `${useRuntimeConfig().public.apiBase}/lesson/${lesson}/getFlashcard` )
        .then( res => {

            pairs.value = res.data.pairs
            length.value = pairs.value.length;

            order.value = shuffleArrayOrder<number>( length.value )
            
            next();

        })
        .catch( err => console.log( err ) );

    const next = () => {

        if( order.value.length <= 0 ) {

            finish();
            return;

        }

        if( !isConfident.value )
            order.value = [ currentIndex.value, ...order.value ];

        currentIndex.value = order.value.pop();
        const pair: [ string, string ] = pairs.value[currentIndex.value-1];

        currentQuestion.value = {

            cantonese: pair[0],
            translation: pair[1],

        }

    }

    const finish = () => {

        isFinished.value = true;

    }

    return { pairs, currentQuestion, next, isFinished, isConfident, order };

}