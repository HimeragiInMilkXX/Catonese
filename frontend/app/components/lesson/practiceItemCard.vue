<script setup lang="ts">

    import { Card, CardContent } from '@/components/ui/card'

    const { image, title, description, lessonId } = defineProps<{ image: string, title: string, description: string, lessonId: string }>()

</script>

<template>
    <nuxt-link :to="`/lessons/${lessonId}/${title}`">
        <Card class="h-full rounded-xl p-0 border shadow bg-cerulean-50 hover:border-cerulean-200 hover:bg-cerulean-100 transition-all border-cerulean-100 shadow-cerulean-50">
            <CardContent class="flex items-center justify-left h-full gap-6 relative box-border max-[639px]:py-3">
                <NuxtImg :src="image" class="aspect-square w-1/5 object-cover max-[832px]:hidden max-[639px]:inline"/>
                <section class="flex flex-col">
                    <h3 class="leading-tight text-2xl font-medium "> {{ title }} </h3>
                    <p class="leading-tight text-cerulean-800!" style="font-size: 0.85rem;"> {{ description }} </p>
                </section>
            </CardContent>
        </Card>
    </nuxt-link>
</template>

<style scoped>

</style>