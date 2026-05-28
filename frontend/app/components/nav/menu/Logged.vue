<script setup lang="ts">

    import { Button } from '../../ui/button';

</script>

<template>
    <ul class="flex items-center gap-3 max-[480px]:gap-0">

        <li>

            <a href="/solutions">

                <Button variant="ghost" class="hover:bg-cerulean-50 hover:text-black text-xl"> Solutions </Button>

            </a>

        </li>

        <li>

            <a href="/company">

                <Button variant="ghost" class="hover:bg-cerulean-50 hover:text-black text-xl"> Company </Button>

            </a>

        </li>

        <li class="ml-1.5 max-[894px]:hidden">

            <nuxt-link href="/lessons">

                <Button variant="ghost" class="cursor-pointer! border-amber-gold-500 text-amber-gold-500! border hover:bg-amber-gold-500 hover:text-white! px-12 py-6 text-xl"> Learn! </Button>

            </nuxt-link>

        </li>

    </ul>
</template>