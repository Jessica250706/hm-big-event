import request from '@/utils/request'
import type { ApiResponse } from '@/utils/request'

export interface articleDTO {
  id: number
  categoryName: string
  categoryAlias: string
  createTime: string
  updateTime: string
}

// 文章分类列表查询
export const articleCategoryListService = (): Promise<ApiResponse<articleDTO[]>> => {
  return request.get('/category')
}
