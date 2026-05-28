import { defineNuxtPlugin } from '#app'
import Chart from 'chart.js/auto'

export default defineNuxtPlugin(() => {
  // Touch Chart so bundler keeps it
  // You can optionally attach it globally if you want:
  // return { provide: { chart: Chart } }
})
