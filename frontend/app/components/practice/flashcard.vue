<script setup lang="ts">

    import { ref } from '#imports';

    const { front, back } = defineProps<{ front: string, back: string }>();
    const isFront = ref<boolean>(true);
    const emits = defineEmits(['next', 'setConfident']);

    import gsap from 'gsap';
    import { onMounted } from 'vue';
    
    const flashcardRef = ref(null);

    const flipAnimation = gsap.timeline( { paused: true });

    onMounted( () => {

        if( !flashcardRef.value ) return;

        flipAnimation.fromTo( flashcardRef.value, {

            rotateY: 0

        }, {

            rotateY: 180,
            duration: 1

        })

        flipAnimation.eventCallback('onComplete', () => {isFront.value = false} )
        flipAnimation.eventCallback('onReverseComplete', () => {isFront.value = true} )

    })

    const flip = () => {

        if( !isFront.value ) {

            flipAnimation.reverse();
            isFront.value = true;

        } else {

            flipAnimation.restart();
            isFront.value = false;

        }

    }

    const nextCard = ( confident: boolean ) => {

        flipAnimation.restart();
        flipAnimation.pause();

        gsap.timeline().to( flashcardRef.value, {
            rotateY: 0,
            duration: 0
        }).eventCallback('onComplete', () => {isFront.value = true} )

        emits('setConfident', confident)
        emits('next')

    }

    defineExpose({ nextCard })

</script>

<template>

    <div class="container perspective-distant aspect-square w-100 max-[683px]:w-[70vw]">

        <div @click="flip" class="flashcard w-full h-full relative" ref="flashcardRef">

            <div class="face front absolute w-full h-full text-white bg-cerulean-300 text-4xl font-medium flex flex-col items-center justify-center rounded-2xl border-cerulean-500 border shadow">{{ front }}</div>
            <div class="face back absolute w-full h-full text-white bg-amber-gold-300 text-4xl font-medium flex flex-col items-center justify-center gap-5 rounded-2xl border-amber-gold-500 border shadow">{{ back }}</div>

        </div>

    </div>

</template>


<style scoped>

    .flashcard {

        transform-style: preserve-3d;

    }

    .face {

        backface-visibility: hidden;

    }

    .back {

        transform: rotateY(180deg);

    }

</style>