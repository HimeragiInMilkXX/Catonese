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
import { NotebookPen } from 'lucide-vue-next'

    definePageMeta( { layout: 'operation'} );

    const formSchema = toTypedSchema(z.object({
            email: z.string()
                .regex(/^[^\s@]+@[^\s@]+\.[^\s@]+$/, "Please provide a valid email"),
            username: z.string().max(16, "Username must be less than 16 characters"),
            password: z.string().min(8, "Password must be at least 8 characters")
                .regex(/[a-z]/, "Must contain a lowercase letter")
                .regex(/[A-Z]/, "Must contain an uppercase letter")
                .regex(/[0-9]/, "Must contain a number")
                .regex(/[!@#$%^&*(),.?":{}|<>]/, "Must contain a symbol"),
            password_confirmation: z.string()
        })
        .refine( data => data.password === data.password_confirmation, {

            message: "Passwords do not match",
            path: ["password_confirmation"]

        }))

    const form = useForm({
        validationSchema: formSchema,
    })

    const { $auth } = useNuxtApp();
    
    const onSubmit = form.handleSubmit((values) => {
        $auth.register( values )
            .then( res => {
                console.log( res )
                navigateTo("/")
            } )
            .catch( err => console.log( err ) );
    })

</script>

<template>
    <form @submit="onSubmit" class="flex flex-col gap-3 box-border p-8 shadow-xl border-2 rounded-2xl justify-center pt-12">

        <section class="flex flex-col items-center gap-1.5 justify-center mb-3">

            <NotebookPen :size="56" :stroke-width="3" class="relative right-0.5"/>
            <h2 class="text-center text-3xl font-bold"> Sign Up </h2>
            <p class="w-2/3 text-center" style="font-size: 0.85rem"> Start your journey! If you already have an account, <nuxt-link to="/auth/login" class="underline text-cerulean-700!">sign in here</nuxt-link> </p>

        </section>

        <FormField v-slot="{ componentField }" name="email">
            <FormItem>
                <FormLabel class="flex items-center">Email <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/> </FormLabel>
                <FormControl>
                    <Input type="email" placeholder="example@mymail.com" v-bind="componentField"/>
                </FormControl>
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="username">
            <FormItem>
                <FormLabel class="flex items-center">Username <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <FormControl>
                    <Input type="text" placeholder="Enter a username..." v-bind="componentField" />
                </FormControl>
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="password">
            <FormItem>
                <FormLabel class="flex items-center">Password <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <PasswordInput :viewable="true" :placeholder="'Enter a password...'" :component-field="componentField" />
            </FormItem>
        </FormField>

        <FormField v-slot="{ componentField }" name="password_confirmation">
            <FormItem>
                <FormLabel class="flex items-center">Password Confirmation <FormMessage class="text-red-400! relative" style="font-size: 0.75rem; line-height: 1;"/></FormLabel>
                <PasswordInput :viewable="true" :placeholder="'Enter your password...'" :component-field="componentField" />
            </FormItem>
        </FormField>

        <Button type="submit" class="mt-3 border-amber-gold-300 hover:border-amber-gold-400 border bg-amber-gold-300 hover:bg-amber-gold-400 text-white!" variant="ghost">
            Sign Up
        </Button>
    </form>
</template>
