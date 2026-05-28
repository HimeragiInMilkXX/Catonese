<script setup lang="ts">
import { LineChart } from 'vue-chart-3'
import type { ChartData, ChartOptions } from 'chart.js'

const props = defineProps<{
  data: ChartData<'line'>
  options?: ChartOptions<'line'>
}>()
</script>

<template>
  <LineChart :chart-data="props.data" :options="props.options"/>
</template>