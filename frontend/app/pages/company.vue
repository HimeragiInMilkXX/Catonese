<script setup lang="ts">

</script>

<template>

    <div class="flex flex-col gap-24 w-full box-border overflow-x-hidden">

        <section class="w-full min-[526px]:mt-12">
            <CompanyHero class="px-12 box-border mx-auto max-w-426.25"/>
        </section>

        <section class="w-full max-[960px]:hidden bg-cerulean-100 py-12">
            <CompanyCoreIdea class="px-12 box-border mx-auto max-w-350.25"/>
        </section>

        <section class="w-full">
            <CompanyData class="px-12 box-border mx-auto max-w-378.25"/>
        </section>
        
        <section class="w-full bg-cerulean-100 py-18" >
            <CompanyProComment class=""/>
        </section>

        <section class="w-full flex flex-col items-center gap-12">

            <h2 class="text-6xl font-bold overflow-x-visible min-[700px]:whitespace-nowrap text-center max-[480px]:hidden"> Start Learning now! </h2>
            <h2 class="text-6xl font-bold overflow-x-visible min-[700px]:whitespace-nowrap text-center min-[480px]:hidden"> Plans </h2>
            <SolutionsPlans class="mx-auto max-w-378.25 px-12"/>

        </section>

    </div>

</template>

<style scoped>

</style>