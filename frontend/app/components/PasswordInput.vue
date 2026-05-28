<script setup lang="ts">

    import type { ComponentFieldBindingObject } from 'vee-validate';
    import { Input } from './ui/input';
    import { ref } from 'vue';
    import { Eye, EyeOff } from 'lucide-vue-next';
    import {
        FormControl,
    } from '@/components/ui/form'

    interface PasswordInputProps {

        placeholder: string;
        componentField?: ComponentFieldBindingObject<any>
        viewable?: boolean

    }

    const { placeholder, componentField, viewable } = defineProps<PasswordInputProps>();

    const allowView = ref( viewable ?? true );

    const isHidden = ref(true);

</script>

<template>

    <div v-if="componentField" class="relative">
        <FormControl class="relative">
            <Input :type="isHidden ? 'password' : 'text'" :placeholder="placeholder" v-bind="componentField"/>
            <Eye v-if="!isHidden" class="absolute right-0 top-1/2 -translate-y-1/2 mr-2 opacity-50" style="margin-top: 0.1rem" :size="24" @click="() => isHidden = true"/>
            <EyeOff v-else class="absolute right-0 top-1/2 -translate-y-1/2 mr-2 opacity-50" style="margin-top: 0.1rem" :size="24" @click="() => isHidden = false"/>
        </FormControl>
    </div>

    <FormControl v-else class="relative">
        <Input type="password" :placeholder="placeholder"/>
    </FormControl>
    
</template>

<style lang="scss" scoped>

    input::-ms-reveal,
    input::-ms-clear {
        display: none;
    }

</style>