<script setup lang="ts">

    import { Card, CardContent } from '@/components/ui/card'
    import { Lock } from 'lucide-vue-next';

    const { lessonId, lesson, unlocked } = defineProps<{ lessonId: string, lesson: {

        name: string;
        goals: string[];

    }, unlocked: boolean}>()

</script>

<template>
    <nuxt-link :to="`/lessons/${lessonId}`" :class="unlocked ? '' : 'blur-[4.5px] pointer-events-none select-none'" class="relative">
        <Card class="bg-center bg-no-repeat bg-cover h-full rounded-xl p-0 border border-gray-100 shadow" :style="{
                'background-image': `linear-gradient(180deg,rgba(255, 255, 255, 0) 0%, rgba(255, 255, 255, 0.95) 80%), url(http://localhost:8080/lessonThumbnail/${lessonId}.png)`
            }">
            <CardContent class="flex flex-col items-start justify-end p-9 h-full gap-1.5 relative">

                <p class="text-2xl"> Lesson {{ lessonId }} </p>
                <h2 class="text-4xl font-bold"> {{ lesson.name }} </h2>

                <ul class="list-disc ml-4.5 pt-1">

                    <li v-for="(goal, index) in lesson.goals.slice(0, 3)" :key="index"> {{ goal }} </li>

                </ul>

            </CardContent>
        </Card>
    </nuxt-link>

    <div class="flex flex-col gap-3 absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2" v-show="!unlocked">

        <Lock :size="36" :stroke-width="3" class="stroke-cerulean-900"/>

    </div>
</template>

<style scoped>

</style>