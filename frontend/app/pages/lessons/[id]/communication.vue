<script setup lang="ts">

    import { useCommunication } from '~/composables/practice/useCommunication';
    import { Button } from '~/components/ui/button';
    import { computed, ref, setPartComplete, watch } from '#imports';
    import MiniAudio from '~/components/miniAudio.vue';
    import gsap from 'gsap';

    const route = useRoute();
    const id = route.params.id;

    const { order, communication, isAnswering, next, finish, isReady, isFinished, currentQuestion, currentOptions, check, currentPartnerLine } = useCommunication(id)

    const activated = ref<string>("");
    const partnerAudioRef = ref();
    const hasStarted = ref<boolean>();
    const attemptable = ref<boolean>(true);

    const remainingcommunication = computed( () => [Object.keys(communication?.value).length - order.value.length])

    const nextQuestion = () => {

        next();

        if( isFinished.value ) return;

        activated.value = '';
        setTimeout( () => partnerAudioRef.value.play() );

    }

    watch( hasStarted, () => {

        activated.value = '';
        setTimeout( () => partnerAudioRef.value.play() );

    })

    function delay() {

        attemptable.value = false;
        setTimeout( () => attemptable.value = true, 800 );

    }

    watch( isFinished, async () => {

        const res = await setPartComplete( "communication" );
        console.log( res );

    })

</script>

<template>

    <section class="relative m-auto box-border h-fit gap-6 w-150 flex flex-col max-[639px]:mx-6 max-[639px]:w-auto bg-amber-gold-50 p-12 rounded-2xl border border-amber-gold-100 shadow shadow-amber-gold-100">
        <div v-if="!hasStarted" class="flex flex-col gap-3 items-center">

            <h2 class="text-4xl font-bold text-center"> How can you response? </h2>
            <p class="text-center" style="font-size: 0.85rem;"> You will be given 4 options for each, first listen to the speaker, then pick the best line to response to the speaker! </p>
            <Button @click="() => hasStarted = true" variant="secondary" class="mt-3 w-full text-2xl px-12 py-6 bg-cerulean-100 border-cerulean-100 text-cerulean-700! hover:bg-cerulean-200"> <Icon name="bxs:right-arrow" :size="18" /> </Button>

        </div>

        <div v-if="!isFinished && communication && hasStarted" class="flex flex-col items-center gap-6">
            <div class="flex flex-col gap-0.5 items-center">
                <p class="flex gap-2 text-3xl items-center"><MiniAudio ref="partnerAudioRef" :audioSource="`/lesson/${id}/getPieceAudio?name=${currentPartnerLine?.id}`" :key="currentPartnerLine?.id" :size="32"/> {{ currentPartnerLine?.content }}</p>
                <p class="text-cerulean-700">{{ currentQuestion?.description }}</p>
            </div>

            <div class="flex flex-col gap-3 items-center w-full box-border">

                <Button v-for="option in currentOptions" :key="option.id" variant="secondary" class="border-amber-gold-400 w-full border bg-amber-gold-300 hover:bg-amber-gold-400 min-[639px]:text-xl box-border py-6 flex justify-center "
                    :class="activated === option.id && 'bg-green-300 border-green-400'"
                    :disabled="!isAnswering"
                    @click="(event: Event) => {

                        if( !attemptable ) return;
                        else delay();

                        const target = event.currentTarget;

                        if( check( option.id ) ) {
                            activated = option.id;
                        } else {
                            gsap.to( event.currentTarget, { duration: 0.8 }).eventCallback( 'onStart', () => target.classList.toggle('wrongAnswer'))
                                    .eventCallback( 'onComplete', () => target.classList.toggle('wrongAnswer') )
                        }

                    }"> 
                    <div class="flex items-center w-4/5 gap-3 max-[639px]:gap-1.5">
                        <MiniAudio :audioSource="`/lesson/${id}/getPieceAudio?name=${option.id}`"/> <p class="text-left whitespace-normal">{{ option.content }}</p>
                    </div>
                </Button>

            </div>

            <Button @click="() => {
                
                nextQuestion()

            }" v-if="!isAnswering" variant="secondary" class="mt-3 w-full text-2xl px-12 py-6 bg-cerulean-100 border-cerulean-100 text-cerulean-700! hover:bg-cerulean-200"> Next </Button>
        </div>

        <div class="flex gap-3 w-full items-center" v-if="currentQuestion && !isFinished && hasStarted">

            <p style="font-size: 0.85rem;" class="leading-tight"> {{ remainingcommunication[0] }}</p>

            <SliderRoot
                v-model="remainingcommunication"
                class="relative flex items-center select-none touch-none pointer-events-none grow h-5  transition-all"
                :default-value="[0]"
                :max="Object.keys(communication).length"
                orientation="horizontal"
            >
                <SliderTrack class="bg-stone-500/30 relative grow rounded-full h-2 transition-all">
                    <SliderRange class="absolute bg-cerulean-100 rounded-full h-full  transition-all" />
                </SliderTrack>
                <SliderThumb
                    class="block opacity-0 bg-cerulean-100 rounded-full"
                    aria-label="Timeline "
                />
            </SliderRoot>

            <p style="font-size: 0.85rem;" class="leading-tight"> {{ communication?.length }}</p>

        </div>

        <div v-if="isFinished" class="p-6 gap-3 h-full text-4xl font-bold text-center flex flex-col items-center">

            <p> Nice Job! </p>
            <Button @click="() => navigateTo(`/lessons/${id}`)" variant="secondary" class="w-fit p-6 px-9 text-2xl relative border-none bg-amber-gold-300 hover:bg-amber-gold-400"> Close </Button>
        
        </div>
    </section>

</template>

<style scoped>

    @keyframes wrongAnswer {

        0% {

            background-color: var(--color-red-400)

        }

        100% {

            background-color: var(--color-amber-gold-300);

        }

    }

    .wrongAnswer {

        animation-duration: 0.8s;
        animation-name: wrongAnswer;

    }

</style>