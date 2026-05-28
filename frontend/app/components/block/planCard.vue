<script setup lang="ts">

    import { Check, X } from 'lucide-vue-next';

    const { iconUrl, name, price, cans, cannots } = defineProps<{ iconUrl: string, name: string, price: string, cans: string[], cannots: string[] }>();

</script>

<template>
    <section class="flex flex-col gap-3 border-2 box-border p-12 items-center rounded-xl shadow hover:brightness-103 transition-all min-w-110 max-w-110 grow max-[460px]:p-6 max-[460px]:min-w-90">

        <Icon :name="iconUrl" :size="96"/>

        <h2 class="text-3xl font-medium"> {{ name }} </h2>

        <div class="flex flex-col items-center min-h-60 w-full gap-3 pt-3">
            
            <slot name="content"/>

            <div class="flex flex-col w-5/6 gap-0.5">
                <p class="italic" style="font-size: 0.85rem"> You can: </p>
                <ul class="w-full flex flex-col gap-0.5">
                    <li v-for="can in cans" :key="can" class="flex items-center gap-1.5"> <Check/> {{ can }} </li>
                </ul>
            </div>

            <div class="flex flex-col w-5/6 gap-0.5">
                <p class="italic" style="font-size: 0.85rem"> You cannot: </p>
                <ul class="w-full flex flex-col gap-0.5">
                    <li v-for="cannot in cannots" :key="cannot" class="flex items-center gap-1.5"> <X/> {{ cannot }} </li>
                </ul>
            </div>
            
        </div>

        <p class="text-3xl pb-3 "> {{ price }}</p>

        <div class="flex flex-col w-full gap-3 items-center">

            <slot name="button"/>

        </div>

    </section>
</template>

<style scoped>

</style>