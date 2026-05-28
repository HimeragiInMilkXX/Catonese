import { type Grammar, type GrammarItem } from "~/types/types";

export const useGrammar = ( lesson: string ) => {

    const grammar = ref<Grammar>();
    const length = ref<number>();
    const currentItem = ref<number>(1);
    const isFinished = ref<boolean>(false);
    const allowNext = ref<boolean>(false);

    $fetch<{data: Grammar, success: boolean}>( `${useRuntimeConfig().public.apiBase}/lesson/${lesson}/getGrammar` )
        .then( res => {

            grammar.value = res.data;
            length.value = Object.keys(grammar.value).length;

        })
        .catch( err => console.log( err ) );

    const next = () => {

        if( !allowNext.value ) return;

        allowNext.value = false;

        if( currentItem.value >= length.value ) {

            finish();
            return;

        }

        currentItem.value++;

    }

    const finish = () => {

        isFinished.value = true;

    }

    const getCurrentGrammar = (): GrammarItem | undefined => {

        return grammar.value![currentItem.value.toString()];

    }

    const getBlanksIndex = (): number[] => {

        const indexes: number[] = [];
        const replaceString = getCurrentGrammar()?.grammar.replace(/\[(\d+)\]/g, "_" );

        replaceString?.replace(/_/g, (match, offset) => {
            indexes.push(offset);
            return "";
        });

        return indexes;

    }

    return { grammar, isFinished, next, currentItem, getCurrentGrammar, allowNext, getBlanksIndex };

}