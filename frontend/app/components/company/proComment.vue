<script setup lang="ts">

    import dummyIcon from "/misc/profile.png"

</script>

<template>

    <section class="flex flex-col gap-18 items-center w-full max-[1140px]:hidden px-12 box-border mx-auto max-w-378.25">

        <BlockImageTextHori :url="dummyIcon" text="The platform’s integration of Spaced Repetition Systems (SRS) is technically sound, but the real value lies in its 'Comprehensible Input' engine. By scaffolding vocabulary within culturally relevant contexts rather than isolated lists, the site effectively triggers the brain’s natural acquisition pathways. It’s a sophisticated balance of pedagogical theory and practical application." title="Dr. Aris Thorne, Applied Linguistics Expert" class="w-full"/>
        <BlockImageTextHori :url="dummyIcon" :reverse="true" text="From a behavioral design standpoint, the gamification loop is impressively low-friction. The interface avoids 'cognitive overload' by utilizing a clean, minimalist hierarchy that directs the learner’s focus toward active production rather than passive clicking. The progress visualization provides just enough dopamine to maintain a daily streak without becoming a distraction from the actual learning material." title="Sarah Chen, Senior Product Designer" class="w-full"/>
        <BlockImageTextHori :url="dummyIcon" text="Most sites fail because they feel like digital textbooks, but this platform prioritizes 'High-Frequency Fluency.' The focus on the first 1,000 most common words—combined with authentic audio from native speakers—ensures that learners can actually hold a conversation in weeks, not years. It’s a high-efficiency tool for the modern, busy learner." title="Luca Marek, Founder of 'The Global Tongue'" class=" w-full"/>

    </section>

    <div class="flex flex-wrap justify-center w-full gap-36 min-[1140px]:hidden px-12 box-border mx-auto max-w-378.25">
        <BlockImageTextVert :url="dummyIcon" title="Dr. Aris Thorne, Applied Linguistics Expert" class="flex-[1_1_256px] max-w-88.75"
            text=""/>
        <BlockImageTextVert :url="dummyIcon" title="Sarah Chen, Senior Product Designer" class="flex-[1_1_256px] max-w-88.75"
            text=""/>
        <BlockImageTextVert :url="dummyIcon" title="Luca Marek, Founder of 'The Global Tongue'" class="flex-[1_1_256px] max-w-88.75" 
            text=""/>
    </div>

</template>

<style scoped>

    .gridContainer {

        grid-template-columns: repeat(auto-fit, minmax( 256px, 1fr))

    }

</style>