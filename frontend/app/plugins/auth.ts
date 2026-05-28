import type { AuthReturn, Register, Login, UserInfo } from "~/types/types";

export default defineNuxtPlugin( () => {

    const satoken = useCookie("satoken");
    const loggedIn = useLoggedIn();
    const userInfo = useUserInfo();

    const apiFetch_noToken = $fetch.create({

        baseURL: useRuntimeConfig().public.apiBase,

        onResponseError({response}) {

            console.log( response );

        }

    })

    const apiFetch_Token = $fetch.create({

        baseURL: useRuntimeConfig().public.apiBase,

        onRequest({options}) {

            if( satoken.value ) {
                const headers = new Headers(options.headers);
                headers.set( "satoken", satoken.value );
                options.headers = headers;
            }

        },

        onResponseError({response}) {

            if( response.status === 401 ) {

                satoken.value = null;
                navigateTo("/auth/login")

            }

        }

    })

    const register = async ( payload: Register ) => {

        const res: AuthReturn = await apiFetch_noToken( "/auth/register", {

            method: "POST",
            body: payload

        });

        const token = res.data.token;

        satoken.value = token;

        userInfo.value = {

            username: res.data.username,
            email: res.data.email,
            userId: res.data.userId,
            nationality: "JP",
            lesson: res.data.lesson,
            bio: "test bio"

        };

        return res;

    }

    const login = async ( payload: Login ) => {

        const res: AuthReturn = await apiFetch_noToken( "/auth/login", {

            method: "POST",
            body: payload,

        })

        const token = res.data.token;

        satoken.value = token;
        loggedIn.value = true

        userInfo.value = {

            username: res.data.username,
            email: res.data.email,
            userId: res.data.userId,
            nationality: "JP",
            lesson: res.data.lesson,
            bio: "test bio"

        };

        return res;
        
    }

    const logout = async() => {

        const res: { success: boolean, data: { message: string } } = await apiFetch_Token( "/auth/logout", { method: "POST" } )

        if( res.success ) {

            satoken.value = null;
            loggedIn.value = false;
            userInfo.value = null;
            navigateTo("/auth/login")

        }

        return res;

    }

    const isLogin = async () => {

        const res: AuthReturn = await apiFetch_Token( "/auth/me", { method: "GET" } );

        userInfo.value = {

            username: res.data.username,
            email: res.data.email,
            userId: res.data.userId,
            nationality: "JP",
            lesson: res.data.lesson,
            bio: "test bio"

        };

        loggedIn.value = res.success;

        return res.success;

    }

    return { provide: { auth: { login, register, logout, isLogin, apiFetch_Token } } };

} )