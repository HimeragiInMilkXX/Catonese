import type { LessonsDetails } from "~/types/types";

export const useStorage = () => {

    const apiBase = useRuntimeConfig().public.apiBase;

    const getJson = async <T>( apiPath: string ): Promise<T> => {

        const { data, status, error } = await useFetch( `${apiBase}${apiPath}` );

        return ( data.value as { succeed: boolean, data: T } ).data as T;

    }

    return { getJson }

}