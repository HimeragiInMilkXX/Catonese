<script setup lang="ts">

    import coreImg from "/company/core-trans.png"

</script>

<template>
    <div class="gap-6 grid min-[960px]:grid-cols-[2fr_1.2fr] place-items-center w-full grid-cols-1">

        <div class="flex flex-col box-border py-12 justify-center items-center gap-6 relative h-full">

            <svg class="w-full h-full relative scale-y-85 origin-top drop-shadow-xl drop-shadow-amber-gold-200" viewBox="0 0 440 198" xmlns="http://www.w3.org/2000/svg">
                <path class="fill-amber-gold-50 stroke-2 stroke-amber-gold-100" d="M58 0.5H380C411.756 0.5 437.5 26.2436 437.5 58V139L437.496 139.711C437.315 154.628 431.453 168.177 421.974 178.298L421.687 178.604L421.939 178.94C423.277 180.721 424.674 182.465 426.062 184.124C429.092 187.748 432.077 190.968 434.304 193.28C435.417 194.436 436.341 195.366 436.986 196.007C437.254 196.273 437.475 196.489 437.641 196.65L437.639 196.653L437.923 196.925C437.926 196.928 437.929 196.931 437.932 196.934C437.954 196.955 437.973 196.973 437.982 196.982L437.983 196.983C437.987 196.987 437.99 196.99 437.993 196.992L437.999 196.998L437.998 196.999H437.995L437.955 197.001C437.947 197.001 437.938 197.001 437.898 197.003C437.828 197.006 437.73 197.011 437.602 197.015C437.34 197.022 436.953 197.03 436.453 197.029C435.454 197.028 434.002 196.995 432.194 196.866C428.577 196.609 423.53 195.967 417.812 194.437C414.196 193.468 410.311 192.142 406.348 190.333L406.129 190.233L405.914 190.342C398.127 194.28 389.324 196.5 380 196.5H58C26.2436 196.5 0.500001 170.756 0.5 139V58C0.500001 26.4917 25.843 0.902783 57.2568 0.504883L58 0.5Z"/>
            </svg>

            <p class="absolute text-2xl font-medium w-5/6 min-[1335px]:top-28.25 top-[8vw] leading-relaxed text-justify">
                It's never about if you can speak fluently, it's about if you are willing to try speaking!
                <span class="max-[1300px]:hidden"> When you stop chasing scores, overcome your fear to speak, that's the moment you get to know the real enjoyment. </span>
            </p>

        </div>

        <img :src="coreImg" alt="" class="object-contain aspect-square relative max-[960px]:absolute max-[960px]:-z-1 max-[960px]:opacity-35">

    </div>
</template>