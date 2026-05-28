<script setup lang="ts">

    import { getProgress, onMounted, ref } from '#imports';
    import { Card, CardContent } from '@/components/ui/card'
    import {
        Dialog,
        DialogContent,
        DialogDescription,
        DialogFooter,
        DialogHeader,
        DialogTitle,
        DialogTrigger,
    } from '@/components/ui/dialog'
    import { Check, X } from 'lucide-vue-next';

    const { image, title, goals } = defineProps<{ image: string, title: string, goals: string[] }>()

    const id = useRoute().params.id;

    const progress = ref();

    onMounted( async () => progress.value = await getProgress( (parseInt(id as string) - 1).toString() ) )

</script>

<template>

    <Dialog>
        <DialogTrigger as-child>

            <Card class="h-full rounded-xl p-0 border shadow bg-amber-gold-100 hover:brightness-102 transition-all border-amber-gold-200 shadow-amber-gold-100 pt-18"  style="grid-area: title">

                <CardContent class="flex flex-col items-center justify-center p-9 h-full gap-1.5 relative max-[757px]:p-3 max-[639px]:p-9">

                    <NuxtImg :src="image" class="relative origin-bottom scale-150"/>

                    <section class="flex flex-col items-center gap-3">

                        <h2 class="text-3xl font-bold"> {{ title }} </h2>

                        <section class="flex flex-col gap-3">

                            <h2> After this lesson, you will be able to... </h2>

                            <ul class="list-disc ml-4.5 pt-1">

                                <li v-for="(goal, index) in goals" :key="index"> {{ goal }} </li>

                            </ul>

                        </section>

                    </section>

                </CardContent>

            </Card>

        </DialogTrigger>
        <DialogContent class="w-fit">
            <DialogHeader>
                <DialogTitle> Progress </DialogTitle>
            </DialogHeader>

            <ul class="list-none">

                <li v-for="(value, key, index) in progress" :key="index" class="flex items-center gap-1.5">

                    <Check class="stroke-green-400" v-if="value"/><X class="stroke-red-400" v-else/>
                    <p class="first-letter:uppercase text-xl"> {{ key }} </p>

                </li>

            </ul>

        </DialogContent>
    </Dialog>

</template>