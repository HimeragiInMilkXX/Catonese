<script setup lang="ts">

    import { Card, CardContent } from '@/components/ui/card'
    import type { Comment } from '~/types/types';
    import { Star } from 'lucide-vue-next';

    const { comment } = defineProps<{comment: Comment}>();

</script>

<template>
    <Card class="py-3">
        <CardContent class="flex flex-col items-center p-5 w-full">
            
            <h3 class="text-2xl italic text-cerulean-800! w-full line-clamp-1"> "{{ comment.title }}</h3>

            <p class="line-clamp-3 text-ellipsis m-3 text-cerulean-900!"> {{ comment.comment }}</p>

            <div class="flex items-center w-full flex-wrap justify-between gap-3">

                <div class="flex items-center gap-1.5 px-1.5">
                    <img
                        :src="`https://purecatamphetamine.github.io/country-flag-icons/3x2/${comment.user.nationality}.svg`"
                        :alt="`${comment.user.nationality} flag`"
                        class="h-4 border"
                    />
                    <p class=""> {{ comment.user.username }} </p>
                </div>

                <BlockStars :rating="comment.rating" :star-size="20" :stroke-width="0.01" class="max-[1400px]:hidden"/>

                <div class="flex items-center gap-1.5 min-[1400px]:hidden">

                    {{ comment.rating }}
                    <Star :size="20"  :stroke-width="0.01" fill="rgba(255, 229, 0, 1)"/>

                </div>

            </div>

        </CardContent>
    </Card>
</template>

<style scoped>

</style>