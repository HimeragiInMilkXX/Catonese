<script setup lang="ts">

    import heroImg from "/home/hero-trans.png"

</script>

<template>
    <div class="gap-3 grid min-[960px]:grid-cols-[1.5fr_1fr] place-items-center w-full grid-cols-1">

        <img :src="heroImg" alt="" class="object-cover aspect-video relative max-[960px]:absolute max-[960px]:-z-1 max-[960px]:opacity-35">

        <section class="flex flex-col box-border p-12 justify-center items-center gap-6 max-[960px]:h-[50vh]">

            <h2 class="text-6xl font-extrabold leading-snug max-[575px]:text-center"> Speak Cantonese in 2 weeks </h2>

            <div class="flex gap-3 w-full max-[575px]:flex-col max-[575px]:items-center">

                <Button variant="ghost" class="text-2xl px-12 py-6 border-cerulean-500 text-cerulean-500! border hover:bg-cerulean-500 hover:text-black! max-[960px]:bg-cerulean-100 max-[960px]:border-cerulean-100 max-[960px]:text-cerulean-700! max-[575px]:hidden"> What is Catonese </Button>
                <Button variant="ghost" class="text-2xl max-[575px]:px-12 px-8 py-6 min-[575px]:grow border-amber-gold-500 text-amber-gold-500! border hover:bg-amber-gold-500 hover:text-white! max-[960px]:bg-amber-gold-300 max-[960px]:border-amber-gold-300 max-[960px]:text-cerulean-800!"> Get Started </Button>

            </div>

        </section>

    </div>
</template>

<style scoped>

</style>

