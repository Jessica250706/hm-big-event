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

export interface articleSearchItem {
  pageNum: number
  pageSize: number
  categoryId?: number
  state?: string
}

export interface articleListDTO {
  total: number
  items: articleDTO[]
}

export interface articleDTO {
  id?: number
  title: string
  content: string
  coverImg: string
  state: string
  categoryId?: number
  categoryName?: string
  createTime?: string
  updateTime?: string
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

// 修改文章分类
export const editArticleCategoryService = (
  categoryData: addArticleCategoryDTO,
): Promise<ApiResponse> => {
  return request.put('/category', categoryData)
}

// 删除文章分类
export const deleteArticleCategoryService = (id: number): Promise<ApiResponse> => {
  return request.delete('/category?id=' + id)
}

// 文章列表查询
export const articleListService = (
  params: articleSearchItem,
): Promise<ApiResponse<articleListDTO>> => {
  return request.get('/article', { params: params })
}

// 添加文章
export const addArticleService = (articleData: articleDTO): Promise<ApiResponse> => {
  return request.post('/article', articleData)
}

// 获取文章详情
export const articleDetailService = (id: number): Promise<ApiResponse<articleDTO>> => {
  return request.get('/article/detail?id=' + id)
}

// 修改文章
export const editArticleService = (articleData: articleDTO): Promise<ApiResponse> => {
  return request.put('/article', articleData)
}

// 删除文章
export const deleteArticleService = (id: number): Promise<ApiResponse> => {
  return request.delete('/article?id=' + id)
}
