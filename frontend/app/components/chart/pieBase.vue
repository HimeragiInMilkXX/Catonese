<script setup lang="ts">
import { DoughnutChart } from 'vue-chart-3'
import type { ChartData, ChartOptions } from 'chart.js'

const props = defineProps<{
  data: ChartData<'doughnut'>
  options?: ChartOptions<'doughnut'>
}>()
</script>

<template>
  <DoughnutChart :chart-data="props.data" :options="props.options" />
</template>