<script setup lang="ts">

    import {
        Carousel,
        CarouselContent,
        CarouselItem,
        CarouselNext,
        CarouselPrevious,
    } from '@/components/ui/carousel'

    import { definePageMeta, getProgress, onMounted, ref } from '#imports';
    import { watchOnce } from '@vueuse/core';
    
    const id = useRoute().params.id;
    const { details } = await useLessons();

</script>

<template>
    <div class="gridContainer grid max-w-3/4 m-auto box-border h-[75vh] max-[639px]:mb-12! gap-6 max-[639px]:flex max-[639px]:flex-col max-[639px]:h-fit max-[639px]:mt-30">
        <LessonTitleCard class="m-0 px-6" :image="'/practice/title.png'" :title="`Lesson ${id}`" :goals="details[id].goals"/>
        <LessonPracticeItemCard :lesson-id="id" :image="'/practice/conversation.png'" title="Conversation" description="Listen to real-life scenario conversation" style="grid-area: conversation"/>
        <LessonPracticeItemCard :lesson-id="id" :image="'/practice/Communication.png'" title="Communication" description="Try to react to real-life scenario" style="grid-area: communication"/>
        <LessonPracticeItemCard :lesson-id="id" :image="'/practice/flashcard.png'" title="Flashcard" description="Remember some vocabs and phrases" style="grid-area: flashcard"/>
        <LessonPracticeItemCard :lesson-id="id" :image="'/practice/Grammar.png'" title="Grammar" description="Learn to build your own sentence" style="grid-area: grammar"/>
        <LessonPracticeItemCard :lesson-id="id" :image="'/practice/Roleplay.png'" title="Roleplay" description="Connect your mind with Catonese" style="grid-area: roleplay"/>
    </div>
</template>

<style scoped>

    .gridContainer {

        grid-template-columns: 1fr 1fr;
        grid-template-rows: 1fr 1fr 1fr 1fr 1fr;

        grid-template-areas:
            "title conversation"
            "title communication"
            "title flashcard"
            "title grammar"
            "title roleplay"

    }

</style>