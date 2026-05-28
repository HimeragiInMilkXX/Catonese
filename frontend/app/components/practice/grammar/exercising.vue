<script setup lang="ts">

    import { useGrammarExercise } from '~/composables/practice/useGrammarExercise';
    import { Button } from '~/components/ui/button';
    import { onMounted, ref, watch } from '#imports';
    import { getWordWithTrans, shuffleArrayOrder, splitTranslation } from '~/utils/Util';
    import { Check } from 'lucide-vue-next';
    import gsap from 'gsap'

    import { Card, CardContent } from '@/components/ui/card'
    import {
        Carousel,
        CarouselContent,
        CarouselItem,
        CarouselNext,
        CarouselPrevious,
    } from '@/components/ui/carousel'
    import {
        Dialog,
        DialogContent,
        DialogDescription,
        DialogFooter,
        DialogHeader,
        DialogTitle,
        DialogTrigger,
    } from '@/components/ui/dialog'

    import type { CarouselApi } from '@/components/ui/carousel'
    import { watchOnce } from '@vueuse/core';

    const { grammar, exercises, blankPositions } = defineProps<{ grammar: string | undefined, exercises: [ ...string[] ] | undefined, blankPositions: number[] }>();

    const { map, check, blanks } = useGrammarExercise( grammar, exercises );

    const activated = ref<{[id: string]: boolean}>({});

    const sentence = ref<{[id: number]: string}>({});

    const counter = ref<number>(0);

    const finished = ref<string[]>([]);

    const buttonOrders = ref({});

    const lineRef = ref<HTMLParagraphElement>();

    const isSelectable = ref<boolean>(true);

    onMounted( () => {

        blanks.value.forEach( ( item ) => {

            sentence.value[item] = "＿"

        })

        let order: number[];

        map.value.forEach( slot => {

            order = shuffleArrayOrder(exercises!.length);

            slot.forEach( word => {

                buttonOrders.value[word] = order.pop();

            })

        })

    })

    function putWord( option: string, index: number) {

        activated.value[option] = true;
        sentence.value[index] = splitTranslation(option, "|")[0];

        if( ++counter.value == blanks.value.length ) {

            finished.value.push(grammar.replace(/\[(\d+)\]/g, (_, n) => sentence.value[n] ?? ""))

            counter.value = 0;

            isSelectable.value = false;

            gsap.timeline().fromTo( lineRef.value, {

                '--highlight-opacity': 0,
                'backgroundColor': 'transparent'

            }, {

                'backgroundColor': 'oklch(79.2% 0.209 151.711)',
                duration: 1,

            }).to( lineRef.value, {

                'backgroundColor': 'transparent',
                duration: 1

            }).to( lineRef.value, {

                duration: 0.5

            }).to( lineRef.value, {

                '--highlight-opacity': 0.5,
                duration: 0

            }).eventCallback( 'onComplete', () => {

                blanks.value.forEach( ( item ) => {

                    sentence.value[item] = "＿"

                })

                current.value = 1;
                api.value?.scrollTo(0, false);

                isSelectable.value = true;

            })

        }

    }

    const emits = defineEmits(['exerciseCompleted'])

    watch( finished.value, () => {

        console.log( finished.value.length, exercises!.length)

        if( finished.value.length >= exercises!.length ) emits('exerciseCompleted')

    } )

    const api = ref<CarouselApi>()
    const totalCount = ref(0)
    const current = ref(0)

    function setApi(val: CarouselApi) {
        api.value = val
    }

    watchOnce(api, (api) => {
        if (!api)
            return
        totalCount.value = api.scrollSnapList().length
        current.value = api.selectedScrollSnap() + 1
        api.on('select', () => {
            current.value = api.selectedScrollSnap() + 1
        })
    })

</script>

<template>
    
    <div class="flex flex-col items-center w-5/6">
        <p class="text-3xl mb-6 question relative before:left-[calc(var(--text-3xl)*var(--blank-position))] transition-all whitespace-nowrap" style="--highlight-opacity: 0.5" :style="{
            '--blank-position': blankPositions[current - 1]
        }" ref="lineRef"> {{ grammar?.replace(/\[(\d+)\]/g, (_, n) => sentence[n] ?? "") }} </p>

        <Carousel class="w-full" @init-api="setApi" :opts="{ loop: true }">
            <CarouselContent>
            <CarouselItem v-for="(blanks, index) in map" :key="index">
                <Card class="bg-transparent border-none shadow-none p-0">
                    <CardContent class="flex flex-col items-center gap-3 p-0" :class="isSelectable ? 'pointer-events-auto' : 'pointer-events-none opacity-50'">
                        <Button v-for="(option, optionIndex) in blanks" :key="optionIndex" class="border-amber-gold-400 w-full border bg-amber-gold-300 hover:bg-amber-gold-400 min-[639px]:text-xl box-border py-6 flex justify-center " variant="secondary"
                            :disabled="activated[option]"
                            :style="{ 'order': buttonOrders[option] }"
                            @click="() => {
                                
                                if( check( optionIndex ) ) putWord( option, index )

                            }"> {{ getWordWithTrans( option, '|') }} </Button>
                    </CardContent>
                </Card>
            </CarouselItem>
            </CarouselContent>
            <CarouselPrevious v-show="totalCount > 1"/>
            <CarouselNext v-show="totalCount > 1"/>
        </Carousel>

        <div class="py-2 text-center text-sm" v-show="totalCount > 1">
            Blank {{ current }} of {{ totalCount }}
        </div>

        <Dialog>
            <DialogTrigger class="underline text-cerulean-700! cursor-pointer text-sm mt-3"> List of completed sentences </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle v-if="finished.length > 0" class="text-3xl"> Completed Sentences</DialogTitle>
                    <DialogTitle v-else> You don't have any complete sentence yet </DialogTitle>
                </DialogHeader>
                <ul class="flex flex-col gap-0.5" v-if="finished.length > 0">

                    <li v-for="(line, index) in finished" :key="index" class="flex items-center gap-1.5 text-xl"> <Check :stroke-width="2.5" class="stroke-green-500"/> {{ line }} </li>

                </ul>
            </DialogContent>
        </Dialog>

    </div>

</template>

<style scoped>

    .question::before {

        content: "";
        width: var(--text-3xl);
        height: 100%;
        display: block;
        position: absolute;
        background-color: var(--color-cerulean-400);
        opacity: var(--highlight-opacity);

    }

</style>