<script setup lang="ts">

    const { url, title, text, position, reverse } = defineProps<{ url: string, title: string, text: string, position?: string, reverse?: boolean }>();
    
</script>

<template>
    <section class="flex gap-12 items-center" :class="reverse ? 'flex-row-reverse pl-12' : 'pr-12'">
        <img :src="url" :alt="text" class="aspect-square object-cover rounded-full w-3/7" :style="{ objectPosition: position }">

        <section class="flex flex-col gap-3 grow ">

            <h2 class="text-4xl font-bold w-full"> {{ title }} </h2>
            <p class="text-justify leading-relaxed comment relative"> <span class="ml-4.5"></span>{{ text }} </p>

        </section>
    </section>
</template>

<style scoped>

    .comment::before {

        content: "“";
        position: absolute;
        font-size: 2rem;
        top: -0.5rem;
        color: var(--color-cerulean-900)

    }

</style>