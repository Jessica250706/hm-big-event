// 定制请求的实例

// 导入axios  npm install axios
import axios from 'axios'
import type { AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'

// 定义统一响应结构（导出供外部使用）
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
}

// 定义一个变量，记录公共的前缀，baseURL
const baseURL = '/api'
const instance = axios.create({ baseURL })

// 添加响应拦截器
instance.interceptors.response.use(
  (result: AxiosResponse) => {
    const { data } = result
    if (data.code === 0) {
      return data
    }
    // 操作失败
    ElMessage.error(data.message ? data.message : '服务异常')
    // 异步操作的状态转换为失败
    return Promise.reject(data)
  },
  (err) => {
    ElMessage.error('服务异常')
    return Promise.reject(err) // 异步的状态转化成失败的状态
  },
)

export default instance
