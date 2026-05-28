<script setup lang="ts">

    import { types } from '~/testData';
    import { Input } from '../ui/input';
    import { Button } from '../ui/button';
    import { Textarea } from '../ui/textarea';
    import {
        Select,
        SelectContent,
        SelectGroup,
        SelectItem,
        SelectLabel,
        SelectTrigger,
        SelectValue,
    } from '@/components/ui/select'

</script>

<template>
    
    <form action="/" class="w-full h-full box-border min-[480px]:p-12 min-[480px]:bg-cerulean-200 flex flex-col gap-6 min-[480px]:rounded min-[480px]:shadow px-4">

        <div class="flex flex-col gap-1.5">

            <Label for="category"> Category </Label>

            <Select>
                <SelectTrigger class="w-full bg-cerulean-50">
                    <SelectValue placeholder="--Select a category--" />
                </SelectTrigger>
                <SelectContent>
                    <SelectItem v-for="type in types" :key="type" :value="type">
                        {{ type }}
                    </SelectItem>
                </SelectContent>
            </Select>

        </div>

        <div class="flex flex-col gap-1.5">

            <Label for="topic"> Topic </Label>
            <Input id="topic" class="w-full bg-cerulean-50 focus-visible:ring-0 focus-visible:border-ring-0"/>

        </div>

        <div class="flex flex-col gap-1.5">

            <Label for="content"> Content </Label>
            <Textarea id="content" class="w-full bg-cerulean-50 focus-visible:ring-0 focus-visible:border-ring-0 resize-none whitespace-pre-wrap break-all max-h-64 h-64"/>

        </div>

        <Button type="submit" class="bg-amber-gold-100 hover:bg-amber-gold-200"> Submit </Button>

    </form>

</template>
