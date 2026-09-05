<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>文章分类</span>
        <div class="extra">
          <el-button type="primary">添加分类</el-button>
        </div>
      </div>
    </template>
    <el-table :data="categories" style="width: 100%">
      <el-table-column label="序号" width="100" type="index" />
      <el-table-column label="分类名称" prop="categoryName" />
      <el-table-column label="分类别名" prop="categoryAlias" />
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" />
          <el-button :icon="Delete" circle plain type="danger" />
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无数据" />
      </template>
    </el-table>
  </el-card>
</template>

<script lang="ts" setup>
import { Edit, Delete } from '@element-plus/icons-vue'
import { onMounted, ref } from 'vue'
import { articleCategoryListService, type articleDTO } from '@/api/article'

const categories = ref<articleDTO[]>([])

const getArticleCategoryList = async () => {
  const result = await articleCategoryListService()
  categories.value = result.data
}

onMounted(() => {
  getArticleCategoryList()
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
}
</style>
