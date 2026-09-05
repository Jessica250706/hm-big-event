import request from '@/utils/request'
import type { ApiResponse } from '@/utils/request'

export interface articleCategoryDTO {
  id: number
  categoryName: string
  categoryAlias: string
  createTime?: string
  updateTime?: string
}

export interface addArticleCategoryDTO {
  id?: number
  categoryName: string
  categoryAlias: string
}

// 文章分类列表查询
export const articleCategoryListService = (): Promise<ApiResponse<articleCategoryDTO[]>> => {
  return request.get('/category')
}

// 添加文章分类
export const addArticleCategoryService = (
  categoryData: addArticleCategoryDTO,
): Promise<ApiResponse> => {
  return request.post('/category', categoryData)
}
