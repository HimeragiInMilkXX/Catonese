export const useAudio = ( endpoint: string ) => {

    const audio = ref<HTMLAudioElement | null>();

    const isPlaying = ref(false);
    const duration = ref(0);
    const currentTime = ref(0);
    const isReady = ref(false);
    const volume = ref(1);

    onMounted( () => {

        audio.value = new Audio( useRuntimeConfig().public.apiBase + endpoint );

        audio.value.addEventListener( "loadedmetadata", () => {

            isReady.value = true;
            duration.value = audio.value!.duration;

        })

        audio.value.addEventListener( "timeupdate", () => {

            currentTime.value = audio.value!.currentTime;

        } )

        audio.value.addEventListener( "play", () => {

            isPlaying.value = true;

        })

        audio.value.addEventListener( "pause", () => {

            isPlaying.value = false;

        })

        audio.value.addEventListener( "volumechange", () => {

            volume.value = audio.value!.volume;

        })

    } );

    const play = () => audio.value!.play();
    const pause = () => audio.value!.pause();
    const fastForward = ( sec: number ) => audio.value!.currentTime += sec;
    const fastBackward = ( sec: number ) => audio.value!.currentTime -= sec;
    const setTime = ( time: number ) => audio.value!.currentTime = time;
    const setVolume = ( volume: number ) => audio.value!.volume = volume;



    return { audio, currentTime, volume, duration, isPlaying, play, pause, fastForward, fastBackward, setTime, setVolume };

}