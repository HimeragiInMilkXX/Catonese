<script setup lang="ts">

    import { Menu, HamburgerIcon, X } from 'lucide-vue-next';

    import {
        Drawer,
        DrawerClose,
        DrawerContent,
        DrawerDescription,
        DrawerFooter,
        DrawerHeader,
        DrawerTitle,
        DrawerTrigger,
    } from '@/components/ui/drawer'
    import Button from '~/components/ui/button/Button.vue';

    import { computed, navigateTo, useNuxtApp, useRoute } from '#imports';

    const { $auth } = useNuxtApp();

    const logout = () => {

        $auth.logout().then( res => {

            if( res.succeed )
                navigateTo("/");

        } ).catch( err => console.log( err ) )

    }

</script>

<template>

    <section class="w-full absolute"><NavHeaderLesson class="mx-auto max-w-429.5 px-12 max-[585px]:px-6"/></section>

    <main class="w-full box-border flex flex-col min-h-dvh"><slot/></main>

    <NavBackButton :back-to-lesson="useRoute().path.split(useRoute().params.id)[1] ? useRoute().params.id : ''"/>

</template>

<style scoped>

</style>