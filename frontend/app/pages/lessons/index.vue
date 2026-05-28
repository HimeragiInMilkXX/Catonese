<script setup lang="ts">

    import {
        Carousel,
        CarouselApi,
        CarouselContent,
        CarouselItem,
        CarouselNext,
        CarouselPrevious,
    } from '@/components/ui/carousel'

    import { definePageMeta, onMounted, ref, useUserInfo } from '#imports';
    import { watchOnce } from '@vueuse/core';
    
    const { details } = await useLessons();

    const currentLesson = ref<number>(1);

    onMounted( () => currentLesson.value = useUserInfo().value!.lesson + 1 )

</script>

<template>
    <Carousel class="relative max-w-10/12 m-auto h-full flex items-center box-border" :opts="{ loop: true, align: 'center' }">
        <CarouselContent class="h-[72vh] m-0">
            <CarouselItem v-for="(lesson, key) in details" :key="key" class="md:basis-[62vh] px-6 relative">
                <LessonMenuCard :lesson-id="key" :lesson="lesson" :unlocked="currentLesson >= parseInt(key)"/>
            </CarouselItem>
        </CarouselContent>
        <CarouselPrevious class="[&_svg]:stroke-cerulean-900 -left-[3.5vw]"/>
        <CarouselNext class="[&_svg]:stroke-cerulean-900 -right-[3.5vw]"/>
    </Carousel>
</template>

<style scoped>

</style>