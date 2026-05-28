export default defineNuxtRouteMiddleware( ( to ) => {

    if( to.path.startsWith( '/lessons' ) ) {

        setPageLayout( "hamburger" );
        
        const nuxtApp = useNuxtApp();

        return nuxtApp.runWithContext( () => {

            const { $auth } = useNuxtApp();

            $auth.isLogin().then( res => {

                if( !res ) navigateTo("/auth/login");

            })
            .catch( err => console.log( err ))

        })

    }


})