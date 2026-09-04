// 定制请求的实例

// 导入axios  npm install axios
import axios from 'axios'
import type { AxiosResponse } from 'axios'

// 定义统一响应结构（导出供外部使用）
export interface ApiResponse<T = any> {
  code: number
  message: string
  data: T
}

// 定义一个变量，记录公共的前缀，baseURL
const baseURL = 'http://localhost:8080'
const instance = axios.create({ baseURL })

// 添加响应拦截器
instance.interceptors.response.use(
  (result: AxiosResponse) => {
    return result.data
  },
  (err) => {
    alert('服务异常')
    return Promise.reject(err) // 异步的状态转化成失败的状态
  },
)

export default instance
