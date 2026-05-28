<script setup lang="ts">

    import { cantoneseLineData, cantoneseLineOptions, learningTimePieData, learningTimePieOptions } from '~/testData';

</script>

<template>
    <div class="flex gap-18 min-[960px]:flex-col max-[810px]:flex-col">

        <div class="flex items-center max-[1210px]:gap-12 max-[960px]:w-2/5 max-[810px]:w-full" >

            <section class="flex-col gap-3 w-3/5 flex items-end max-[960px]:hidden">

                <h2 class="text-4xl font-bold"> Designed for practical cases </h2>
                <p class="text-xl leading-relaxed text-end"> All lessons are designed to help you get into practical daily scenario and application, conversations are designed by native speakers, at minimal but enough extent we try to involve local slangs  </p>

            </section>

            <ChartPieBase :data="learningTimePieData" :options="learningTimePieOptions" class="grow"/>

        </div>

        <div class="flex gap-12 items-center max-[810px]:justify-center max-[960px]:w-3/5 max-[810px]:w-full">

            <ChartLineBase :data="cantoneseLineData" :options="cantoneseLineOptions" class="max-[810px]:w-4/5 min-[810px]:grow"/>

            <section class="flex-col gap-3 w-3/5 flex max-[960px]:hidden">

                <h2 class="text-4xl font-bold"> Spreading the culture </h2>
                <p class="text-xl leading-relaxed "> Despite decrease of Cantonese speaker in Hong Kong, the population of cantonese speakers is going up every year, you can be one of them. Let's contribute protecting the origin of modern Chinese!   </p>

            </section>

        </div>

    </div>
</template>
