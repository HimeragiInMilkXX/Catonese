<script setup lang="ts">

    import {
        Dialog,
        DialogContent,
        DialogDescription,
        DialogFooter,
        DialogHeader,
        DialogTitle,
        DialogTrigger,
    } from '@/components/ui/dialog'
    import Button from '../ui/button/Button.vue';
    import { ref } from 'vue';
    import { ProfileFormExpose, type UserInfo } from '~/types/types';
    import defaultProfile from "/default.jpg"
    import { ImgUploadEl } from './ImageUpload.vue';

    const isEditing = ref<boolean>(false);

    const formRef = ref<ProfileFormExpose>();
    const imgUploadRef = ref<ImgUploadEl>();

    const quitEdit = () => {

        isEditing.value = false;
        formRef.value?.resetForm()
        imgUploadRef.value?.resetInput()

    }

    const startEdit = () => { isEditing.value = true }

    const saveEdit = () => {
        const formValues: UserInfo | undefined = formRef.value?.getFormValues()
        const profileImg: File | undefined = imgUploadRef.value?.getUploadedImg()
        quitEdit();
    }

</script>

<template>
    <Dialog>
        <DialogTrigger class="py-1.5 px-2 text-[14px] w-full text-left hover:bg-gray-100/90 rounded-[6px]">Profile</DialogTrigger>
        <DialogContent class="max-w-none! w-130">
            <DialogHeader>
                <DialogTitle class="text-4xl ml-1.5">Profile</DialogTitle>
            </DialogHeader>
            <div class="flex gap-6">
                <div class="basis-0 grow flex flex-col gap-3">
                    <ProfileImageUpload ref="imgUploadRef" class="w-full aspect-square border rounded-full"/>
                    <div class="flex flex-col gap-1.5 grow" v-if="!isEditing">
                        <Button variant="secondary" class="basis-0 grow bg-blue-200 shadow hover:bg-blue-300/70" @click="startEdit"> Edit </Button>
                        <Button variant="secondary" class="basis-0 grow bg-red-200 shadow hover:bg-red-300/70"> Reset </Button>
                    </div>
                    <div class="flex flex-col gap-1.5 grow" v-else>
                        <Button variant="secondary" class="basis-0 grow bg-green-200 shadow hover:bg-green-300/70" @click="saveEdit"> Save </Button>
                        <Button variant="secondary" class="basis-0 grow bg-yellow-200 shadow hover:bg-yellow-300/70" @click="quitEdit"> Cancel </Button>
                    </div>
                </div>
                <FormProfile class="basis-0 grow-[1.6]" ref="formRef"/>
            </div>
        </DialogContent>
    </Dialog>
</template>

<style scoped>

</style>