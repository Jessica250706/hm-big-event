<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>文章分类</span>
        <div class="extra">
          <el-button type="primary" @click="showAddDialog">添加分类</el-button>
        </div>
      </div>
    </template>
    <el-table :data="categories" style="width: 100%">
      <el-table-column label="序号" width="100" type="index" />
      <el-table-column label="分类名称" prop="categoryName" />
      <el-table-column label="分类别名" prop="categoryAlias" />
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showEditDialog(row)" />
          <el-button
            :icon="Delete"
            circle
            plain
            type="danger"
            @click="deleteArticleCategory(row.id)"
          />
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="暂无数据" />
      </template>
    </el-table>
    <!-- 添加分类弹窗 -->
    <el-dialog v-model="dialogVisible" title="添加弹层" width="30%">
      <el-form
        :model="categoryModel"
        :rules="rules"
        label-width="100px"
        style="padding-right: 30px"
      >
        <el-form-item label="分类名称" prop="categoryName">
          <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10" />
        </el-form-item>
        <el-form-item label="分类别名" prop="categoryAlias">
          <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button
            type="primary"
            @click="title === '添加分类' ? addArticleCategory() : editArticleCategory()"
          >
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
  </el-card>
</template>

<script lang="ts" setup>
import { Edit, Delete } from '@element-plus/icons-vue'
import { onMounted, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  articleCategoryListService,
  addArticleCategoryService,
  editArticleCategoryService,
  deleteArticleCategoryService,
} from '@/api/article'
import type { articleCategoryDTO, addArticleCategoryDTO } from '@/api/article'

const dialogVisible = ref(false)
const title = ref<string>('')
const categories = ref<articleCategoryDTO[]>([])
const categoryModel = ref<addArticleCategoryDTO>({
  categoryName: '',
  categoryAlias: '',
})

const rules = {
  categoryName: [{ required: true, message: '请输入分类名称', trigger: 'blur' }],
  categoryAlias: [{ required: true, message: '请输入分类别名', trigger: 'blur' }],
}

const getArticleCategoryList = async () => {
  const result = await articleCategoryListService()
  categories.value = result.data
}

const clearCategoryModel = () => {
  categoryModel.value.categoryAlias = ''
  categoryModel.value.categoryName = ''
}

const showAddDialog = () => {
  dialogVisible.value = true
  title.value = '添加分类'
  clearCategoryModel()
}

const showEditDialog = (row: any) => {
  dialogVisible.value = true
  title.value = '修改分类'
  // 数据拷贝
  categoryModel.value.categoryName = row.categoryName
  categoryModel.value.categoryAlias = row.categoryAlias
  categoryModel.value.id = row.id
}

const addArticleCategory = async () => {
  const { message } = await addArticleCategoryService(categoryModel.value)
  ElMessage.success(message ? message : '添加成功')
  // 刷新
  getArticleCategoryList()
  // 关闭弹窗
  dialogVisible.value = false
  clearCategoryModel()
}

const editArticleCategory = async () => {
  const { message } = await editArticleCategoryService(categoryModel.value)
  ElMessage.success(message ? message : '修改成功')
  // 刷新
  getArticleCategoryList()
  // 关闭弹窗
  dialogVisible.value = false
  clearCategoryModel()
}

const deleteArticleCategory = (id: number) => {
  // 弹窗
  ElMessageBox.confirm('确认要删除该分类吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      await deleteArticleCategoryService(id)
      ElMessage.success('删除成功')
      // 刷新
      getArticleCategoryList()
    })
    .catch(() => {
      ElMessage.info('取消删除')
    })
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
