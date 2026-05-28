<script setup lang="ts">

    import shadowingImg from "/home/shadowing-trans.png"
    import speakingImg from "/home/speaking-trans.png"

</script>

<template>
    <div class="gap-6 grid place-items-center gridContainer w-full bg-cerulean-100 box-border max-[668px]:pb-16">

        <div class="grid grid-cols-[1fr_1.1fr] place-items-center gap-3 max-[668px]:grid-cols-1 max-[668px]:gap-0 relative max-[668px]:w-3/4">

            <img :src="speakingImg" alt="" class="object-cover aspect-square relative scale-110 bottom-8">

            <section class="flex flex-col gap-3 max-[668px]:items-center">

                <h2 class="text-3xl font-bold max-[668px]:text-center whitespace-nowrap"> Shadowing and Mimic </h2>
                <p class="max-[668px]:text-center max-[668px]:w-3/5"> Instead of boring recition and writing, let's get our hands first on speaking and start learning from applying! </p>

            </section>

        </div>

        <div class="grid grid-cols-[1fr_1.1fr] place-items-center gap-3 max-[668px]:grid-cols-1 max-[668px]:gap-0 relative">

            <img :src="shadowingImg" alt="" class="object-cover aspect-square relative scale-95 top-4 max-[668px]:w-3/4">

            <section class="flex flex-col gap-3 max-[668px]:items-center">

                <h2 class="text-3xl font-bold max-[668px]:text-center whitespace-nowrap"> Real-life Conversation </h2>
                <p class="max-[668px]:text-center max-[668px]:w-3/5"> Let's engage in real-life communication and scenario, and Oscar wants to let you know also slangs and its culture! </p>

            </section>

        </div>
        
    </div>
</template>

<style scoped>

    .gridContainer {

        @media screen and ( min-width: 668px ) {
            grid-template-columns: repeat(auto-fit, minmax( 585px, 1fr))
        }

    }

</style>