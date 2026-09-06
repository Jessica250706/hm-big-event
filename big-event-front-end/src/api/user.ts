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

export interface pwdDataDTO {
  oldPwd: string
  newPwd: string
  rePwd: string
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

// 修改个人信息
export const updateUserInfoService = (userInfoData: userInfoDTO): Promise<ApiResponse> => {
  return request.put('/user/update', userInfoData)
}

// 修改头像
export const updateUserAvatarService = (avatarUrl: string): Promise<ApiResponse> => {
  const params = new URLSearchParams()
  params.append('avatarUrl', avatarUrl)
  return request.patch('/user/updateAvatar', params)
}

// 修改密码
export const updateUserPasswordService = (pwdData: pwdDataDTO): Promise<ApiResponse> => {
  const params = {
    old_pwd: pwdData.oldPwd,
    new_pwd: pwdData.newPwd,
    re_pwd: pwdData.rePwd,
  }
  return request.patch('/user/updatePwd', params)
}
