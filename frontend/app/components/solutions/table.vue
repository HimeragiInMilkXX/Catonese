<script setup lang="ts">

    import {
        Table,
        TableBody,
        TableCaption,
        TableCell,
        TableFooter,
        TableHead,
        TableHeader,
        TableRow,
    } from '@/components/ui/table'
    import { Check, X } from 'lucide-vue-next';

    const plans = [
        { privileges: "Access first 5 lessons", access: [ true, true, true ] },
        { privileges: "Unlock all lessons", access: [ false, true, true ] },
        { privileges: "Post in community", access: [ true, true, true ] },
        { privileges: "Full community access", access: [ false, true, true ] },
        { privileges: "View community events", access: [ false, true, true ] },
        { privileges: "Apply for meetups", access: [ false, true, true ] },
        { privileges: "Join regular meetups", access: [ false, true, true ] },
        { privileges: "Free meetup entry", access: [ false, false, true ] },
        { privileges: "Online support access", access: [ false, false, true ] },
        { privileges: "Priority chat help", access: [ false, false, true ] },
        { privileges: "Support other users", access: [ false, false, true ] },
        { privileges: "Take over Catonese", access: [ false, false, false ] }
    ]

</script>

<template>
    <div>
        <Table>
            <TableCaption>Privileges of each tier</TableCaption>
            <TableHeader>
                <TableRow>
                    <TableHead class="p-6"></TableHead>
                    <TableHead class="text-3xl text-center w-1/3 p-6">Soft Paw</TableHead>
                    <TableHead class="text-3xl text-center w-1/3 p-6">Sharp Whisker</TableHead>
                    <TableHead class="text-3xl text-center w-1/3 p-6">Night Vision</TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                <TableRow v-for="(plan, index) in plans" :key="index">
                    <TableCell class="text-xl p-6 text-end"> {{ plan['privileges'] }} </TableCell>
                    <TableCell class="p-6 text-center"> <Check v-if="plan['access'][0]" :size="30" class="m-auto"/> <X v-else :size="30" class="m-auto"/> </TableCell>
                    <TableCell class="p-6 text-center"> <Check v-if="plan['access'][1]" :size="30" class="m-auto"/> <X v-else :size="30" class="m-auto"/> </TableCell>
                    <TableCell class="p-6 text-center"> <Check v-if="plan['access'][2]" :size="30" class="m-auto"/> <X v-else :size="30" class="m-auto"/> </TableCell>
                </TableRow>
            </TableBody>
        </Table>
    </div>
</template>

<style scoped>

</style>