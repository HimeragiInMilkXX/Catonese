export const setPartComplete = async ( part: "communication" | "flashcard" | "grammar" | "roleplay" ) => {

    const { $auth } = useNuxtApp();
    const res = await $auth.apiFetch_Token( `/progress/complete/${part}`, { method: "PUT" } )

    return res;

}

export const getProgress = async ( lesson: String ) => {

    const { $auth } = useNuxtApp();
    const res = await $auth.apiFetch_Token<{ success: boolean, data: { [key: string]: boolean } }>( `/progress/get/${lesson}`, { method: "GET" } )

    const progressMap = {

        "communication": false,
        "flashcard": false,
        "grammar": false,
        "roleplay": false

    }

    Object.entries( res.data ).map( ( [ key, value ]) => {

        progressMap[ key as keyof typeof progressMap ] = value

    })

    return progressMap;

}