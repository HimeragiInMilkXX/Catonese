<script setup lang="ts">

    import { BarChart } from 'vue-chart-3'
    import type { ChartData, ChartOptions } from 'chart.js'

    const { data, options } = defineProps<{

        data: ChartData<'bar'>
        options?: ChartOptions<'bar'>

    }>();

</script>

<template>
    <BarChart :chart-data="data" :options="options"/>
</template>