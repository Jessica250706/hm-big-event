import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { userInfoDTO } from '@/api/user'

const useUserInfoStore = defineStore(
  'userInfo',
  () => {
    const initialInfo = {
      id: undefined as number | undefined,
      username: '',
      password: '',
      nickname: '',
      email: '',
      userPic: '',
    }

    const info = ref<userInfoDTO>({ ...initialInfo })

    const setInfo = (newInfo: any) => {
      info.value = newInfo
    }

    const removeInfo = () => {
      info.value = { ...initialInfo }
    }

    return {
      info,
      setInfo,
      removeInfo,
    }
  },
  { persist: true },
)

export default useUserInfoStore
