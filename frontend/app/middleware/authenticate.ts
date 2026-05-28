export default defineNuxtRouteMiddleware( () => {

    const { $auth } = useNuxtApp();
    const nuxtApp = useNuxtApp();

    return nuxtApp.runWithContext( () => {

        $auth.isLogin().then( res => {

            if( !res ) navigateTo("/auth/login");

        })

    })



})