<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>文章管理</span>
        <div class="extra">
          <el-button type="primary">添加文章</el-button>
        </div>
      </div>
    </template>
    <!-- 搜索表单 -->
    <el-form inline>
      <el-form-item label="文章分类：">
        <el-select placeholder="请选择" v-model="searchParam.categoryId">
          <el-option
            v-for="c in categories"
            :key="c.id"
            :label="c.categoryName"
            :value="c.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布状态：">
        <el-select placeholder="请选择" v-model="searchParam.state">
          <el-option label="已发布" value="已发布"></el-option>
          <el-option label="草稿" value="草稿"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getArticleList">搜索</el-button>
        <el-button @click="resetSearchItems">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 文章列表 -->
    <el-table :data="articles" style="width: 100%">
      <el-table-column label="文章标题" width="400" prop="title"></el-table-column>
      <el-table-column label="分类" prop="categoryName"></el-table-column>
      <el-table-column label="发表时间" prop="createTime"></el-table-column>
      <el-table-column label="状态" prop="state"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary"></el-button>
          <el-button :icon="Delete" circle plain type="danger"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
    <!-- 分页条 -->
    <el-pagination
      v-model:current-page="pageHelper.pageNum"
      v-model:page-size="pageHelper.pageSize"
      :page-sizes="[3, 5, 10, 15]"
      layout="jumper, total, sizes, prev, pager, next"
      background
      :total="pageHelper.total"
      @size-change="onSizeChange"
      @current-change="onCurrentChange"
      style="margin-top: 20px; justify-content: flex-end"
    />
  </el-card>
</template>

<script lang="ts" setup>
import { Edit, Delete } from '@element-plus/icons-vue'
import { onMounted, ref } from 'vue'
import { articleCategoryListService, articleListService } from '@/api/article'
import type { articleCategoryDTO, articleDTO } from '@/api/article'

// 文章分类数据模型
const categories = ref<articleCategoryDTO[]>([])
// 搜索框数据
const searchParam = ref({
  categoryId: null as number | null, // 用户搜索时选中的分类id
  state: '', // 用户搜索时选中的发布状态
})
// 文章列表数据模型
const articles = ref<articleDTO[]>([])

// 分页条数据模型
const pageHelper = ref({
  pageNum: 1, // 当前页
  total: 20, // 总条数
  pageSize: 3, // 每页条数
})

// 当每页条数发生变化
const onSizeChange = (size: number) => {
  pageHelper.value.pageSize = size
  getArticleList()
}
// 当前页码发生变化，调用此函数
const onCurrentChange = (num: number) => {
  pageHelper.value.pageNum = num
  getArticleList()
}

const getArticleCategoryList = async () => {
  const result = await articleCategoryListService()
  categories.value = result.data
}

const resetSearchItems = () => {
  // 清空
  searchParam.value.categoryId = null
  searchParam.value.state = ''
  // 刷新
  getArticleList()
}

const getArticleList = async () => {
  const params = {
    pageNum: pageHelper.value.pageNum,
    pageSize: pageHelper.value.pageSize,
    categoryId: searchParam.value.categoryId ?? undefined,
    state: searchParam.value.state.length !== 0 ? searchParam.value.state : undefined,
  }
  const { data } = await articleListService(params)
  pageHelper.value.total = data.total
  articles.value = data.items
}

onMounted(() => {
  getArticleCategoryList()
  getArticleList()
})
</script>

<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-select {
    --el-select-width: 220px;
  }
}
</style>
