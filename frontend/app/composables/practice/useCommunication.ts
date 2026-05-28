import type { Communication, CommunicationQuestion, Conversation, Line } from "~/types/types";

export const useCommunication = ( lesson: string ) => {

    const communication = ref<Communication>();
    const conversation = ref<Conversation>();

    const isReady = ref<boolean>(false);
    const isAnswering = ref<boolean>(false);

    const isFinished = ref<boolean>(false);

    const length = ref<number>(0);
    const order = ref<string[]>([]);

    const current = ref<string>("0");
    const currentQuestion = ref<CommunicationQuestion>();
    const currentPartnerLine = ref<Line>();
    const currentOptions = ref<Line[]>([]);

    $fetch<Conversation>( `${useRuntimeConfig().public.apiBase}/lesson/${lesson}/getConversation` )
        .then( res => {

            conversation.value = res;

            $fetch<{data: Communication, success: boolean}>( `${useRuntimeConfig().public.apiBase}/lesson/${lesson}/getCommunication` )
                .then( res => {

                    communication.value = res.data;
                    length.value = Object.keys(communication.value).length;
                    
                    order.value = shuffleArrayOrder<string>( length.value )

                    isReady.value = true;
                    
                    next();

                })
                .catch( err => console.log( err ) );

        }).catch( err => console.log( err ) )

    const generateOptions = (): Line[] => {

        const answer = currentQuestion.value?.answer;

        const role = answer?.match(/([A-Za-z])(\d+)/)[1];

        let options: Line[] | undefined;
        let possibleWrongOptions: Line[] | undefined;

        switch( role ) {

            case "A":
                options = conversation.value?.a.lines.filter( ( line: Line ) => line.id === answer );
                possibleWrongOptions = conversation.value?.a.lines.filter( ( line: Line ) => line.id !== answer );
                break;

            case "B":
                options = conversation.value?.b.lines.filter( ( line: Line ) => line.id === answer );
                possibleWrongOptions = conversation.value?.b.lines.filter( ( line: Line ) => line.id !== answer );
                break;

        }


        let random = shuffleArrayOrder<number>( possibleWrongOptions!.length );
        options?.push( possibleWrongOptions[random.pop() - 1] )
        options?.push( possibleWrongOptions[random.pop() - 1] )
        options?.push( possibleWrongOptions[random.pop() - 1] )

        random = shuffleArrayOrder<number>( 4 );
        options = [ options[random.pop() - 1], options[random.pop() - 1], options[random.pop() - 1], options[random.pop() - 1] ]

        return options;

    }

    const check = ( choice: string ): boolean => {

        if( choice === currentQuestion.value?.answer ) {
            isAnswering.value = false;
            return true;
        }

        return false;

    }

    const next = () => {

        if( !isReady.value ) return;

        if( order.value.length <= 0 ) {

            finish();
            return;

        }

        current.value = order.value.pop();
        currentQuestion.value = communication.value[current.value];
        currentOptions.value = generateOptions();
        currentPartnerLine.value = [...conversation.value?.a.lines, conversation.value?.b.lines].filter( ( line: Line ) => line.id === currentQuestion.value?.question )[0]

        isAnswering.value = true;
    }

    const finish = () => {

        isFinished.value = true;

    }

    return { communication, isAnswering, next, finish, isReady, isFinished, currentQuestion, currentOptions, check, currentPartnerLine, order }

}