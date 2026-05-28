<script setup lang="ts">

    const { backToLesson } = defineProps<{ backToLesson: string }>();

</script>

<template>
    <nuxt-link :to="`/lessons/${backToLesson}`" class="flex gap-1.5 fixed right-0 bottom-0 p-4.5 items-center hover:*:text-cerulean-700! z-50 select-none">

        <p class="leading-tight text-xl"> Back </p>
        <Icon name="bxs:right-arrow" :size="18" />

    </nuxt-link>
</template>