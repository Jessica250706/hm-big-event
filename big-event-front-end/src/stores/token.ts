import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useTokenStore = defineStore(
  'token',
  () => {
    // 1.响应式变量
    const token = ref<string>('')

    // 2.定义一个函数，修改 token 的值
    const setToken = (newToken: string) => {
      token.value = newToken
    }

    // 3.定义一个函数，移除 token 的值
    const removeToken = () => {
      token.value = ''
    }

    return {
      token,
      setToken,
      removeToken,
    }
  },
  {
    persist: true, // 持久化存储
  },
)
