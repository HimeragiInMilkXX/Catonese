<script setup lang="ts">

    import { Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious } from '@/components/ui/carousel'
    import AutoScroll from 'embla-carousel-auto-scroll'
    import { comments } from '~/testData';

</script>
<template>
  <div class="w-full">
        <Carousel
            class="relative w-full"
            :opts="{
                loop: true,
                dragFree: true,
                containScroll: false,
            }"
            :plugins="[
                AutoScroll({
                speed: 0.5,  // Lower = slower. Try values between 0.5 - 2
                stopOnInteraction: false,
                })
            ]"
        >
            <CarouselContent>
                <CarouselItem v-for="(comment, index) in comments" :key="index" class="md:basis-1/2 lg:basis-1/4">
                    <div class="p-1.5"><BlockComment :comment="comment"/></div>
                </CarouselItem>
            </CarouselContent>
        </Carousel>
  </div>
</template>