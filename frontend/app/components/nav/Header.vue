<script setup lang="ts">

    

</script>

<template>

    <header class="box-border pt-6 pb-3">

        <div class="flex justify-between items-center">

            <NavLogo/>

            <NavMenuDefault v-if="!useLoggedIn().value"/>
            <NavMenuLogged v-else/>

        </div>

    </header>
    
</template>

<style scoped>

</style>