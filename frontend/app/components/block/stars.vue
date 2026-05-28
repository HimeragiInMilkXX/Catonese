<script setup lang="ts">

    import { Star } from 'lucide-vue-next';

    const { rating, starSize, strokeWidth = 0 } = defineProps<{ rating: number, starSize: number, strokeWidth?: number }>()

    function getFillProportion( starCount: number ): string {

        if( rating / starCount >= 1 ) return "100%";
        
        const decimal = parseFloat(rating.toString().split(".")[1]) / 10;

        return `${decimal * 100}%`

    }

</script>

<template>
    <div class="flex gap-0.5">

        <div v-for="i in 5" :key="i">

            <svg width="0" height="0" v-if="Math.ceil(rating) >= i">
                <defs>
                    <linearGradient :id="`myGradient${i}`" x1="0%" y1="0%" x2="100%" y2="0%">
                        <stop offset="0%" style="stop-color:rgba(255, 229, 0, 1)" />
                        <stop :offset="getFillProportion(i)" style="stop-color:rgba(255, 229, 0, 1)" />
                        <stop :offset="getFillProportion(i)" style="stop-color:rgba(255, 255, 255, 0)" />
                        <stop offset="100%" style="stop-color:rgba(255, 255, 255, 0)" />
                    </linearGradient>
                </defs>
            </svg>
            
            <Star class="gradient-icon" :size="starSize" :style="`fill: url(#myGradient${i})`" :stroke-width="strokeWidth" v-if="Math.ceil(rating) >= i"/>

        </div>

    </div>
</template>

<style scoped>

</style>