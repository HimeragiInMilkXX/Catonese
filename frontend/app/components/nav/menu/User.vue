<script setup lang="ts">

    import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
    } from '@/components/ui/dropdown-menu'
    import { EllipsisVertical } from 'lucide-vue-next';
    import profileImg from "/misc/profileLizard.jpeg"
    import { navigateTo, useNuxtApp } from '#imports';

    const { $auth } = useNuxtApp();

    const logout = () => {

        $auth.logout().then( res => {

            if( res.succeed )
                navigateTo("/");

        } ).catch( err => console.log( err ) )

    }

</script>

<template>

    <div class="flex gap-3 items-center h-10 justify-between">

        <div class="flex items-center gap-3 h-full">
            <img :src="profileImg" class="rounded-full object-cover h-full" alt="">
            <div class="flex flex-col">
                <p> {{ useUserInfo().value?.username }} </p>
                <p style="font-size: 0.75rem"> Lesson 1 </p>
            </div>
        </div>

        <DropdownMenu>
            <DropdownMenuTrigger as-child><EllipsisVertical :size="24"/></DropdownMenuTrigger>
            <DropdownMenuContent>
                <DropdownMenuItem as-child><ProfileDialog/></DropdownMenuItem>
                <DropdownMenuItem @click="logout">Logout</DropdownMenuItem>
                <DropdownMenuItem>Reset</DropdownMenuItem>
            </DropdownMenuContent>
        </DropdownMenu>
    </div>

</template>