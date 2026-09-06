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
        <el-button type="primary">搜索</el-button>
        <el-button>重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 文章列表 -->
    <el-table :data="articles" style="width: 100%">
      <el-table-column label="文章标题" width="400" prop="title"></el-table-column>
      <el-table-column label="分类" prop="categoryId"></el-table-column>
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
import { articleCategoryListService } from '@/api/article'
import type { articleCategoryDTO, articleDTO } from '@/api/article'

// 文章分类数据模型
const categories = ref<articleCategoryDTO[]>([])
// 搜索框数据
const searchParam = ref({
  categoryId: '', // 用户搜索时选中的分类id
  state: '', // 用户搜索时选中的发布状态
})
// 文章列表数据模型
const articles = ref([
  {
    id: 5,
    title: '陕西旅游攻略333',
    content: '兵马俑,华清池,法门寺,华山...爱去哪去哪...',
    coverImg:
      'https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png',
    state: '草稿',
    categoryId: 2,
    createTime: '2023-09-03 11:55:30',
    updateTime: '2023-09-03 11:55:30',
  },
  {
    id: 5,
    title: '陕西旅游攻略222',
    content: '兵马俑,华清池,法门寺,华山...爱去哪去哪...',
    coverImg:
      'https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png',
    state: '草稿',
    categoryId: 2,
    createTime: '2023-09-03 11:55:30',
    updateTime: '2023-09-03 11:55:30',
  },
  {
    id: 5,
    title: '陕西旅游攻略11',
    content: '兵马俑,华清池,法门寺,华山...爱去哪去哪...',
    coverImg:
      'https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png',
    state: '草稿',
    categoryId: 2,
    createTime: '2023-09-03 11:55:30',
    updateTime: '2023-09-03 11:55:30',
  },
])

// 分页条数据模型
const pageHelper = ref({
  pageNum: 1, // 当前页
  total: 20, // 总条数
  pageSize: 3, // 每页条数
})

// 当每页条数发生变化
const onSizeChange = (size: number) => {
  pageHelper.value.pageSize = size
}
// 当前页码发生变化，调用此函数
const onCurrentChange = (num: number) => {
  pageHelper.value.pageNum = num
}

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

  .el-select {
    --el-select-width: 220px;
  }
}
</style>
