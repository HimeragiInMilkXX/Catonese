<script setup lang="ts">

    const { url, title, text, position } = defineProps<{ url: string, title: string, text: string, position?: string }>();
    
</script>

<template>
    <section class="flex flex-col gap-6">
        <img :src="url" :alt="text" class="aspect-square object-cover rounded-full" :style="{ objectPosition: position }">

        <section class="flex flex-col gap-3 w-full">

            <h2 class="text-3xl font-bold w-full text-center"> {{ title }} </h2>
            <p class="text-justify" style="text-align-last: center"> {{ text }} </p>

        </section>
    </section>
</template>