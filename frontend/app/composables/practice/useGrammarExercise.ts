export const useGrammarExercise = ( grammar: string | undefined, exercises: [ ...string[] ] | undefined ) => {

    if( !grammar || !exercises ) return;

    const blanks = ref<number[]>([ ...grammar.matchAll(/\[(\d+)\]/g) ].map( m => parseInt( m[1] ) ));

    const map = ref<[...string[]]>([]);

    blanks.value.forEach( blank => {

        map.value.push([]);

        exercises.forEach( pair => {

            map.value[blank]?.push( pair[blank] );

        })

    })

    const choice = ref<number | null>( null );
    const counter = ref<number>(0);

    const check = ( index: number ): boolean => {

        if( counter.value >= blanks.value.length ) {

            choice.value = null;
            counter.value = 0;

        }

        if( !choice.value?.toString() ) {

            choice.value = index;
            counter.value++;
            return true;

        }

        if( index.toString() != choice.value.toString() ) {

            return false;

        } else {

            counter.value++;
            return true;

        }

    }

    return { map, check, blanks };

}