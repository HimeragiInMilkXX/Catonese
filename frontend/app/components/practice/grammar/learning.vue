<script setup lang="ts">

    const { grammar, explanation, examples } = defineProps<{ grammar: string | undefined, explanation: string | undefined, examples: [ [ string, string ] ] | undefined}>()

</script>

<template>
    <div class="box-border flex flex-col gap-3 max-h-4/5 overflow-y-scroll max-[1002px]:h-fit max-[1002px]:overflow-y-visible max-[1002px]:pt-12">

        <p class="text-3xl">{{ grammar && replaceWithSymbol( grammar, /\[\d+\]/g, "～") }}</p>
        <p class="mt-2 mb-2">{{ explanation }}</p>

        <p> Examples: </p>

        <ul class="list-disc box-border pl-4">

            <li v-for="([ cantonese, translation ], index) in examples" :key="index">

                <span> {{ cantonese }} </span> <span> ({{ translation }}) </span>

            </li>

        </ul>
    </div>
</template>

<style scoped>

</style>