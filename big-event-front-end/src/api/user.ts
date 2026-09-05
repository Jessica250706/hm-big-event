// 导入 request.ts 请求工具
import request from '@/utils/request'
import type { ApiResponse } from '@/utils/request'

export const userRegisterService = (registerData: any): Promise<ApiResponse> => {
  const params = new URLSearchParams()
  for (let key in registerData) {
    params.append(key, registerData[key])
  }
  // 显式取出 response.data，类型自动推导为 ApiResponse
  return request.post('/user/register', params)
}

export const userLoginService = (registerData: any): Promise<ApiResponse> => {
  const params = new URLSearchParams()
  for (let key in registerData) {
    params.append(key, registerData[key])
  }
  // 显式取出 response.data，类型自动推导为 ApiResponse
  return request.post('/user/login', params)
}
