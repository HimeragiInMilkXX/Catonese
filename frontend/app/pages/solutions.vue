<script setup lang="ts">

    import heroImg from "/misc/solutionback.png"

</script>

<template>
    <div class="flex flex-col gap-24 w-full box-border overflow-x-hidden mt-6">

        <section class="w-full" :style="`background: linear-gradient(90deg,var(--color-cerulean-50) 50%, #e6f8ff88 75%, #e6f8ff00 100%), url(${heroImg}) center / cover no-repeat`" style="">
            <SolutionsHero class="px-12 box-border mx-auto max-w-426.25"/>
        </section>

        <section class="w-full">
            <SolutionsTable class="max-w-378.25 mx-auto px-12 py-16"/>
        </section>

        <section class="w-full flex flex-col items-center gap-6">

            <h2 class="text-4xl font-bold overflow-x-visible min-[700px]:whitespace-nowrap text-center max-[480px]:hidden"> Start Learning now! </h2>
            <h2 class="text-4xl font-bold overflow-x-visible min-[700px]:whitespace-nowrap text-center min-[480px]:hidden"> Plans </h2>
            <SolutionsPlans class="mx-auto max-w-378.25 px-12"/>

        </section>

    </div>
</template>

<style scoped>

</style>