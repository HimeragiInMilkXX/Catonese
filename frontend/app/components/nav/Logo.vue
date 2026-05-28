<script setup lang="ts">

    import { Cat } from 'lucide-vue-next';

</script>

<template>
    <nuxt-link to="/" class="flex items-center gap-3 relative z-50">
        <Cat :size="60"/>
        <h1 class="text-4xl font-extrabold max-[570px]:hidden">Catonese</h1>
    </nuxt-link>
</template>