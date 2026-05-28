<script setup lang="ts">

    import { definePageMeta, onMounted, ref, useAudio, useRoute, useRuntimeConfig, watch } from '#imports';
    import { watchOnce } from '@vueuse/core';
    import AudioPlayer from '~/components/AudioPlayer.vue';
    import { Button } from '~/components/ui/button';
    import type { Conversation, Line } from '~/types/types';

    const route = useRoute();

    const lesson = route.params.id;

    const conversation = ref<Conversation>();
    const currentIndex = ref<number>(0);
    const scriptEnabled = ref<boolean>(true);
    const conversationLength = ref<number>();

    const audioPlayerRef = ref<typeof AudioPlayer>();
    const scrollableRef = ref<HTMLDivElement>();

    const orderedLines = ref<Line[]>([]);
    const orderedTimestamps = ref<number[]>([]);

    $fetch<Conversation>( `${useRuntimeConfig().public.apiBase}/lesson/${lesson}/getConversation` )
        .then( res => {
            
            conversation.value = res;
            order();

        } )
        .catch( err => console.log( err ) );

    function scrollToCurrentLine() {

        const el = scrollableRef.value;

        if( el && el.children.length > 0 )
            el.scrollTo( {
                top: el.children[0].offsetHeight * currentIndex.value + 24 * ( currentIndex.value - 1 ),
                behavior: 'smooth'
            } )

    }

    function checkNextLine( seconds: number ) {

        if( audioPlayerRef.value?.isTimeDragged ) {

            findClosest( seconds );
            return;

        }

        if( currentIndex.value + 1 >= conversationLength.value ) return;

        if( seconds > orderedTimestamps.value[ currentIndex.value + 1 ] ) currentIndex.value++;

    }

    function findClosest( seconds: number ) {

        for( let i = 0; i < conversationLength.value + 1; i++ ) {

            if( orderedTimestamps.value[i] > seconds ) {

                currentIndex.value = i - 1;
                break;

            }

        }

        audioPlayerRef.value?.returnState();

    }

    function order() {

        conversationLength.value = conversation.value?.a.lines.length * 2;

        for( let i = 0; i < conversationLength.value / 2; i++ ) {

            orderedLines.value.push( conversation.value?.a.lines[i]?.content );
            orderedLines.value.push( conversation.value?.b.lines[i]?.content );

            orderedTimestamps.value.push( toSeconds( conversation.value?.a.lines[i]?.timestamp ) );
            orderedTimestamps.value.push( toSeconds( conversation.value?.b.lines[i]?.timestamp ) );
        
        }

    }

    onMounted( () => {

        watchOnce( () => audioPlayerRef.value?.duration, ( duration ) => {

            orderedTimestamps.value.push( duration );

        })

        watch( currentIndex, () => { scrollToCurrentLine(); })

    })

</script>

<template>
    <div class="max-w-2/3 max-[875px]:mb-12! m-auto box-border h-fit gap-6 max-[875px]:flex max-[875px]:flex-col max-[875px]:h-fit max-[875px]:mt-30 flex bg-amber-gold-50 p-12 rounded-2xl border border-amber-gold-100 shadow shadow-amber-gold-100">
        <AudioPlayer :audioSource="`/lesson/${lesson}/getConversationAudio`" :imageSource="`http://localhost:8080/lessonThumbnail/${lesson}.png`" class="min-[875px]:w-1/2"
            @nextLine="(seconds) => checkNextLine(seconds)" ref="audioPlayerRef"/>
        <section class="min-[875px]:w-1/2 flex flex-col gap-6 box-border pb-12 py-3 justify-center">
            <div class="flex justify-between items-center">

                <h2 class="text-2xl font-bold truncate"> <span class="max-[1306px]:hidden">Lesson {{lesson}} - </span>Conversation </h2>

                <Button variant="secondary" class="bg-red-200 leading-tight hover:bg-red-300" v-if="scriptEnabled" @click="() => scriptEnabled = false"> Script off </Button>
                <Button variant="secondary" class="bg-green-200 leading-tight hover:bg-green-300" v-else @click="() => scriptEnabled = true"> Script on </Button>

            </div>
            <div class="flex flex-col gap-6 overflow-y-scroll box-border overflow-scroll grow min-[875px]:h-[20vw]" :class="!scriptEnabled && 'blur-[5px]'" v-if="orderedLines.length > 0" style="scrollbar-width: none" ref="scrollableRef">
                <div v-for="i in conversationLength" :key="i" class="flex justify-between items-center">

                    <p class="transition-all" :class="(currentIndex == i-1 && scriptEnabled )? 'text-4xl font-extrabold text-cerulean-700!' : 'text-xl'"> {{ orderedLines[i-1] }}</p>
                    <p style="font-size: 0.85rem;"> {{ orderedTimestamps[i-1] }}</p>

                </div>
            </div>
        </section>
    </div>

</template>

<style scoped>

</style>