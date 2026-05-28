<script setup lang="ts">

    import { definePageMeta } from '#imports'
    import loginCat from "/misc/loginCat.png"

    definePageMeta( { layout: 'operation'} );

</script>

<template>
    <div class="grid grid-cols-2 max-w-260 mx-auto items-center relative h-[58vh] gap-18 px-12 max-[774px]:grid-cols-1 mb-6">
        <img :src="loginCat" alt="" class="relative scale-125 min-w-118.75 loginCat max-[774px]:hidden drop-shadow-2xl">
        <FormLogin class="rounded h-full bg-amber-gold-50 border-amber-gold-100"/>
    </div>
</template>

<style scoped>

    .loginCat {

        @media screen and ( max-width: 1040px ) {
            
            left: calc( ( 100vw - 1040px ) / 2 )

        }

    }

</style>