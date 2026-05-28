import { type RoleplayQuestion, type Roleplay } from "~/types/types"

export const useRoleplay = ( lesson: string ) => {

    const roleplay = ref<Roleplay>();

    const isReady = ref<boolean>(false);
    const isAnswering = ref<boolean>(false);

    const isFinished = ref<boolean>(false);
    const finalScore = ref<number>(0);

    const length = ref<number>(0);
    const order = ref<string[]>([]);

    const current = ref<string>("0");
    const currentQuestion = ref<RoleplayQuestion>();

    const totalScore = ref<number>(0);

    $fetch<{data: Roleplay, success: boolean}>( `${useRuntimeConfig().public.apiBase}/lesson/${lesson}/getRoleplay` )
        .then( res => {

            roleplay.value = res.data;
            length.value = Object.keys(roleplay.value).length;
            
            order.value = shuffleArrayOrder<string>( length.value )

            isReady.value = true;
            
            next();

        })
        .catch( err => console.log( err ) );

    const handleScore = ( score: number, transcription: string ): number => {

        let confidenceScore = score * 100;

        isAnswering.value = false;

        const transcriptionChar = [...ignoreDynamic(transcription)];
        const referenceChar = [...removePunctuation(currentQuestion.value?.answer)];

        transcriptionChar.forEach( (char, index) => {

            if( !(char == referenceChar[index]) ) confidenceScore -= 100 / referenceChar.length;

        })

        if( confidenceScore < 0 ) confidenceScore = 0;

        totalScore.value += confidenceScore;

        return confidenceScore;

    }

    const removePunctuation = ( textString: string ): string => {

        const charArray = [ ...textString ]

        const filtered = charArray.filter( ( char ) => char != "，" && char != " " && char != "？" && char != "。" && char != "！" );

        return filtered.join('');

    }

    const ignoreDynamic = ( textString: string ): string => {

        if( !currentQuestion.value?.ignore ) return textString;

        const inputString = textString;

        let processedString = textString;

        currentQuestion.value?.ignore.forEach( ( ignore: [ number, number, string ]  ) => {

            const referenceString = removePunctuation(currentQuestion.value?.answer);

            const startFrom: number = ignore[0];
            const endAt: number = ignore[1];
            const replaceBy: string = ignore[2];

            const endCharAppearAt = inputString.indexOf( referenceString.charAt(endAt) );

            processedString = `${inputString.slice(0, startFrom)}${replaceBy}${inputString.slice(endCharAppearAt)}`

        })

        console.log( processedString );

        return processedString;

    }

    const next = () => {

        if( !isReady.value ) return;

        if( order.value.length <= 0 ) {

            finish();
            return;

        }

        current.value = order.value.pop();
        currentQuestion.value = roleplay.value[current.value];

        isAnswering.value = true;

    }
    
    const finish = () => {

        isFinished.value = true;

        finalScore.value = totalScore.value / length.value;

    }

    return { finalScore, next, handleScore, currentQuestion, isAnswering, removePunctuation, isFinished };

}