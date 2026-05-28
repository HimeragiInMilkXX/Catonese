<script setup lang="ts">

    import monitoringImg from "/home/monitoring-trans.png"
    import shorttimeImg from "/home/shorttime-trans.png"
    import communityImg from "/home/community-trans.png"
    import affordableImg from "/home/affordable.png"

</script>

<template>
    <div class="flex flex-wrap justify-center w-full gap-36">
        <BlockImageTextVert :url="monitoringImg" title="24/7 Support" position="35%" class="flex-[1_1_256px] max-w-88.75"
            text="Online locals 24/7 support about system and even cantonese learning for premium users!"/>
        <BlockImageTextVert :url="shorttimeImg" title="Just 30 minutes" position="60% 5px" class="flex-[1_1_256px] max-w-88.75"
            text="During break, after a busy day, anytime, enjoy the language and culture, it's never a burden!"/>
        <BlockImageTextVert :url="communityImg" title="Vast Community" position="50% -20px" class="flex-[1_1_256px] max-w-88.75" 
            text="A community of learners from all around the world, also don't miss our regular meetups!"/>
    </div>
</template>

<style scoped>

    .gridContainer {

        grid-template-columns: repeat(auto-fit, minmax( 256px, 1fr))

    }

</style>