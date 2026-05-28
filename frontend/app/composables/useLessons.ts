import type { LessonsDetails } from "~/types/types";

export const useLessons = async () => {

    const { getJson } = useStorage();

    const details = useState<LessonsDetails>("lessons:details" );
    const loaded = useState<boolean>( "lessons:loaded", () => false );

    if( !loaded.value ) {

        const data = await getJson<LessonsDetails>("/lesson/getDetails")
        details.value = data;

    }

    return { details };
    
}