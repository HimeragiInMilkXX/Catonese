<script setup lang="ts">
    
    import { definePageMeta, navigateTo, useNuxtApp } from '#imports'
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
    import { Input } from '@/components/ui/input'
    import type { UserInfo } from '~/types/types'
    import { LogIn } from 'lucide-vue-next'

    const { $auth } = useNuxtApp();

    const formSchema = toTypedSchema(z.object({
            identifier: z.string().nonempty("Required"),
            password: z.string().nonempty("Required")
        }))

    const form = useForm({
        validationSchema: formSchema,
    })

    const onSubmit = form.handleSubmit((values) => {
        $auth.login( values )
            .then( res => {
                navigateTo("/lessons")
            } )
            .catch( err => console.log( err ) );
    })

</script>

<template>
    <form @submit="onSubmit" class="flex flex-col gap-3 box-border p-8 shadow-xl border-2 rounded-2xl justify-center">

        <section class="flex flex-col items-center gap-1.5 justify-center mb-3">

                <LogIn :size="56" :stroke-width="3" class="relative right-0.5"/>
                <h2 class="text-center text-3xl font-bold"> Sign In </h2>
            <p class="w-2/3 text-center" style="font-size: 0.85rem"> Oscar is urging you to continue your journey of learning Cantonese, if not yet, start it by <nuxt-link to="/auth/register" class="underline text-cerulean-700!">signing up</nuxt-link> </p>

        </section>

        <FormField v-slot="{ componentField }" name="identifier">
            <FormItem>
                <FormLabel class="flex items-center">Username / Email <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <FormControl>
                    <Input type="text" placeholder="Enter your username or email" v-bind="componentField" />
                </FormControl>
                
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="password">
            <FormItem>
                <FormLabel class="flex items-center">Password <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <PasswordInput :viewable="true" :placeholder="'Enter your password...'" :component-field="componentField" />
            </FormItem>
        </FormField>

        <Button type="submit" class="mt-3 border-amber-gold-300 hover:border-amber-gold-400 border bg-amber-gold-300 hover:bg-amber-gold-400 text-white!" variant="ghost">
            Sign In
        </Button>
    </form>
</template>

<style scoped>

</style>