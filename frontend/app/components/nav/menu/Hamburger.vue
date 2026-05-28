<script setup lang="ts">

    import { Menu, HamburgerIcon, X, Cat } from 'lucide-vue-next';

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


</script>

<template>

    <Drawer class="relative">
        <DrawerTrigger class="relative z-50"><Menu :size="40" :stroke-width="3"/></DrawerTrigger>
        <DrawerContent data-vaul-drawer-direction="right" class="max-w-72!">

            <DrawerTitle class="text-2xl hidden"> Catonese </DrawerTitle>

            <ul class="flex flex-col gap-3 p-6">

                <li>
                    <nuxt-link to="" class="cursor-pointer"> Continue Learning </nuxt-link>
                </li>
                <li>
                    <nuxt-link to="" class="cursor-pointer"> Review Materials </nuxt-link>
                </li>
                <li>
                    <nuxt-link to="" class="cursor-pointer"> Common Q&A </nuxt-link>
                </li>
                <li>
                    <nuxt-link to="/" class="cursor-pointer"> Main Page </nuxt-link>
                </li>

            </ul>

            <DrawerFooter class="w-full border-t p-3">
                <NavMenuUser/>
            </DrawerFooter>

        </DrawerContent>
    </Drawer>
    
</template>

<style scoped>

</style>