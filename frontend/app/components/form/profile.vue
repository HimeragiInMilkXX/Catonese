<script setup lang="ts">
    
    import { computed, definePageMeta, navigateTo, useNuxtApp, useUserInfo } from '#imports'
    import { toTypedSchema } from '@vee-validate/zod'
    import { useForm } from 'vee-validate'
    import * as z from 'zod'

    import { Button } from '@/components/ui/button'
    import {
        FormControl,
        FormDescription,
        FormField,
        FormItem,
        FormLabel,
        FormMessage,
    } from '@/components/ui/form'
    import { Textarea } from '@/components/ui/textarea'
    import { Input } from '@/components/ui/input'
import type { ProfileFormExpose } from '~/types/types'

    const { $auth } = useNuxtApp();

    const userInfo = computed( () => useUserInfo().value );

    const formSchema = toTypedSchema(z.object({
        email: z.string()
            .regex(/^[^\s@]+@[^\s@]+\.[^\s@]+$/, "Please provide a valid email"),
        username: z.string().max(16, "Username must be less than 16 characters"),
        nationality: z.string().nullish(),
        bio: z.string().nullish()
    }))

    const form = useForm({
        validationSchema: formSchema,
        initialValues: userInfo.value
    })

    defineExpose<ProfileFormExpose>( { getFormValues: () => { return form.values }, resetForm: () => form.resetForm() })

</script>

<template>
    <form class="flex flex-col gap-4.5 box-border">

        <FormField v-slot="{ componentField }" name="email">
            <FormItem class="gap-0">
                <FormLabel class="flex items-center text-xs">Email <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <FormControl>
                    <Input type="email" placeholder="Enter your email" v-bind="componentField" class="px-1.5 py-0.5 h-fit outline-none focus-visible:ring-0 border-0 shadow-none border-b rounded-none"/>
                </FormControl>
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="username">
            <FormItem class="gap-0">
                <FormLabel class="flex items-center text-xs">Username <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <FormControl>
                    <Input type="text" placeholder="Enter your username" v-bind="componentField" class="px-1.5 py-0.5 h-fit outline-none focus-visible:ring-0 border-0 shadow-none border-b rounded-none"/>
                </FormControl>
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="nationality">
            <FormItem class="gap-0">
                <FormLabel class="flex items-center text-xs">Nationality <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <FormControl>
                    <Input type="text" placeholder="Enter your nationality" v-bind="componentField" class="px-1.5 py-0.5 h-fit outline-none focus-visible:ring-0 border-0 shadow-none border-b rounded-none"/>
                </FormControl>
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="bio">
            <FormItem class="gap-1.5">
                <FormLabel class="flex items-center text-xs">Bio <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <FormControl>
                    <Textarea placeholder="Enter your biography" v-bind="componentField" class="outline-none focus-visible:ring-0 shadow-none rounded-none resize-none overflow-y-scroll [scrollbar-width:none] max-h-11.5"/>
                </FormControl>
            </FormItem>
        </FormField>

    </form>
</template>

<style scoped>

</style>