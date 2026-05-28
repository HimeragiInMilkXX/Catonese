<script setup lang="ts">

    import { useNuxtApp } from '#imports';
    import { Button } from '~/components/ui/button';

    const { $auth } = useNuxtApp();

    const logout = () => {

        $auth.logout().then( res => console.log( res ) ).catch( err => console.log( err ) )

    }

</script>

<template>

    <section class="w-full"><NavHeader class="mx-auto max-w-426.25 px-12 max-[585px]:px-6"/></section>

    <main class="w-full box-border items-center flex flex-col"><slot/></main>

    <section class="w-full bg-cerulean-50"><NavFooter class="max-auto max-w-402.25 px-24"/></section>
    
</template>