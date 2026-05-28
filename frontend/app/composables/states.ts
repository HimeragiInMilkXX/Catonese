import type { UserInfo } from "~/types/types"

export const useLoggedIn = () => useState<boolean>('loggedIn', () => false )
export const useUserInfo = () => useState<UserInfo | null>('userInfo', () => null )