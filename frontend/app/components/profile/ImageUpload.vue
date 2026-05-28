<script setup lang="ts">

    import Label from '../ui/label/Label.vue';
    import Input from '../ui/input/Input.vue';
    import { Upload } from 'lucide-vue-next';
    import type { HTMLInputEvent } from '~/types/types';
    import { ref } from 'vue';

    const { url } = defineProps<{ url?: string }>();
    const preview = ref<HTMLImageElement>();

    const uploadImg = ref<File>();

    const onFileUpload = (e: HTMLInputEvent) => {

        const file: File | undefined = e.target?.files![0];

        if( !file ) throw new Error("File isn't uploaded successfully!")

        uploadImg.value = file;

        const reader = new FileReader();
        reader.onload = () => {preview.value.src = reader.result}
        reader.readAsDataURL( file );

    }

    export interface ImgUploadEl { getUploadedImg: () => File | undefined, resetInput: () => void }

    defineExpose<ImgUploadEl>( {
        getUploadedImg: () => {return uploadImg.value},
        resetInput: () => {

            uploadImg.value = undefined;
            preview.value!.src = "";

        }
    } )

</script>

<template>
    <div class="flex justify-center items-center relative overflow-hidden hover:opacity-80 transition-all">

        <Label for="imgUpload" class="absolute w-full h-full z-2 opacity-0"> <Upload/> </Label>
        <Input @change="onFileUpload" name="imgUpload" id="imgUpload" accept="image/*" type="file" class="hidden"/>
        <img :src="url ?? 'default.jpg'" alt="" class="absolute w-full h-full object-cover" v-if="!uploadImg">
        <img ref="preview" alt="" class="absolute w-full h-full object-cover z-1" v-else>

    </div>
</template>

<style scoped>

</style>