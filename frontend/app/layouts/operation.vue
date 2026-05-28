<script setup lang="ts">

    import { useNuxtApp } from '#imports';
    import { Button } from '~/components/ui/button';

    const { $auth } = useNuxtApp();

    const logout = () => {

        $auth.logout().then( res => console.log( res ) ).catch( err => console.log( err ) )

    }

</script>

<template>

    <section class="w-full absolute"><NavHeaderOperation class="mx-auto max-w-429.5 px-12 max-[585px]:px-6"/></section>

    <main class="w-full box-border items-center flex flex-col h-dvh justify-center"><slot/></main>
    
</template>