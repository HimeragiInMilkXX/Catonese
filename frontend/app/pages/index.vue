<script setup lang="ts">

</script>

<template>

    <div class="flex flex-col gap-24 w-full box-border overflow-x-hidden">

        <section class="w-full">
            <HomeHero class="px-12 box-border mx-auto max-w-426.25"/>
        </section>

        <section class="w-full bg-cerulean-100">
            <HomeExpectation class="max-w-426.25 mx-auto px-12 max-[668px]:px-0"/>
        </section>

        <section class="w-full">
            <HomeFeatures class="max-w-378.25 mx-auto px-12"/>
        </section>

        <section class="w-full">
            <HomeVoice class="max-w-426.25 mx-auto px-12 py-16"/>
        </section>

        <section class="w-full flex flex-col items-center gap-12">

            <h2 class="text-6xl font-bold overflow-x-visible min-[700px]:whitespace-nowrap text-center max-[480px]:hidden"> Start Learning now! </h2>
            <h2 class="text-6xl font-bold overflow-x-visible min-[700px]:whitespace-nowrap text-center min-[480px]:hidden"> Plans </h2>
            <SolutionsPlans class="mx-auto max-w-378.25 px-12"/>

        </section>

        <section class="w-full bg-cerulean-100">

            <HomeEnquiry class="px-12 my-8 mx-auto max-w-402.25 max-[460px]:p-4"/>

        </section>

    </div>

</template>