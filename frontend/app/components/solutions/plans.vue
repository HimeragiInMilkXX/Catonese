<script setup lang="ts">
import { Button } from '../ui/button';




</script>

<template>
    <div class="flex flex-wrap justify-center w-full gap-6">

        <BlockPlanCard icon-url="mdi:paw" name="Soft Paw" price="Free" class="border-cerulean-100 bg-cerulean-50"
            :cans="['Access to first 5 lessons', 'Make posts in community']"
            :cannots="['Access to all lessons', 'Join regular meetups', 'Reach for Online Support']">

            <template v-slot:content>

                <p> Give the first few lessons a try first! </p>

            </template>
            
            <template v-slot:button>

                <Button variant="ghost" class="w-5/6 py-4 border-cerulean-300 text-cerulean-300! border hover:bg-cerulean-200 hover:text-white!"> Learn More </Button>
                <Button variant="ghost" class="text-xl px-8 py-6 border-cerulean-500 text-cerulean-500! border hover:bg-cerulean-400 hover:text-white! w-5/6"> Get Started </Button>

            </template>
        </BlockPlanCard>

        <BlockPlanCard icon-url="mdi:eye" name="Sharp Whisker" price="$10/month" class="border-amber-gold-200 bg-amber-gold-100"
            :cans="['Full Access to all lessons', 'Full Access Community', 'Apply regular meetups']"
            :cannots="['Join meetups for free', 'Reach for Online Support']">

            <template v-slot:content>

                <p> Learn and start engaging in events! </p>

            </template>
            
            <template v-slot:button>

                <Button variant="ghost" class="w-5/6 py-4 border-amber-gold-400 text-amber-gold-400! border hover:bg-amber-gold-300 hover:text-white!"> Learn More </Button>
                <Button variant="ghost" class="text-xl px-8 py-6 border-amber-gold-600 text-amber-gold-600! border hover:bg-amber-gold-500 hover:text-white! w-5/6"> Try Catonese </Button>

            </template>
        </BlockPlanCard>

        <BlockPlanCard icon-url="fluent:animal-cat-28-filled" name="Night Vision" price="$15/month" class="border-amber-gold-400 bg-amber-gold-300"
            :cans="['Full Access to all lessons', 'Full Access Community', 'Full regular meetups', 'Reach for Online Support']"
            :cannots="['Take over Catonese']">

            <template v-slot:content>

                <p> Become also a supporter of others! </p>

            </template>
            
            <template v-slot:button>

                <Button variant="ghost" class="w-5/6 py-4 border-amber-gold-600 text-amber-gold-600! border hover:bg-amber-gold-500 hover:text-white!"> Learn More </Button>
                <Button variant="ghost" class="text-xl px-8 py-6 border-amber-gold-800 text-amber-gold-800! border hover:bg-amber-gold-700 hover:text-white! w-5/6"> Try Catonese </Button>

            </template>
        </BlockPlanCard>

    </div>
</template>
    
<style scoped>

</style>