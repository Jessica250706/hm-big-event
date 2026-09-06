import request from '@/utils/request'
import type { ApiResponse } from '@/utils/request'

export interface userInfoDTO {
  id?: number
  username: string
  password: string
  nickname: string
  email: string
  userPic: string
  createTime?: string
  updateTime?: string
}

// 用户注册
export const userRegisterService = (registerData: any): Promise<ApiResponse> => {
  const params = new URLSearchParams()
  for (let key in registerData) {
    params.append(key, registerData[key])
  }
  return request.post('/user/register', params)
}

// 用户登录
export const userLoginService = (registerData: any): Promise<ApiResponse> => {
  const params = new URLSearchParams()
  for (let key in registerData) {
    params.append(key, registerData[key])
  }
  return request.post('/user/login', params)
}

// 获取用户详细信息
export const userInfoService = (): Promise<ApiResponse<userInfoDTO>> => {
  return request.get('/user/userInfo')
}
