<template>
  <!-- 抽屉 -->
  <el-drawer v-model="visibleDrawer" title="添加文章" direction="rtl" size="50%">
    <!-- 添加文章表单 -->
    <el-form :model="articleModel" label-width="100px">
      <el-form-item label="文章标题">
        <el-input v-model="articleModel.title" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="文章分类">
        <el-select placeholder="请选择" v-model="articleModel.categoryId">
          <el-option v-for="c in categories" :key="c.id" :label="c.categoryName" :value="c.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="文章封面">
        <el-upload
          class="avatar-uploader"
          :auto-upload="true"
          :show-file-list="false"
          action="/api/upload"
          name="file"
          :headers="{ Authorization: tokenStore.token }"
          :on-success="uploadSuccess"
        >
          <img v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon">
            <Plus />
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="文章内容">
        <div class="editor">
          <quill-editor theme="snow" v-model:content="articleModel.content" contentType="html" />
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addArticle('已发布')">发布</el-button>
        <el-button type="info" @click="addArticle('草稿')">草稿</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
</template>

<script lang="ts" setup>
import { Plus } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { useTokenStore } from '@/stores/token'
import type { ApiResponse } from '@/utils/request'
import { addArticleService } from '@/api/article'
import type { articleCategoryDTO, articleDTO } from '@/api/article'
import { ElMessage } from 'element-plus'

const props = defineProps<{
  categories: articleCategoryDTO[]
  getArticleList: Function
}>()

const tokenStore = useTokenStore()

// 抽屉是否显示
const visibleDrawer = ref(false)
const openDrawer = () => {
  visibleDrawer.value = true
}

const articleModel = ref({
  title: '',
  categoryId: '',
  coverImg: '',
  content: '',
  state: '',
})

const uploadSuccess = (result: ApiResponse) => {
  articleModel.value.coverImg = result.data
  console.log('data:', result.data)
}

const addArticle = async (clickState: string) => {
  articleModel.value.state = clickState
  if (!articleModel.value.categoryId) {
    ElMessage.warning('请选择文章分类')
    return
  }
  const params: articleDTO = {
    ...articleModel.value,
    categoryId: Number(articleModel.value.categoryId),
  }
  const { message } = await addArticleService(params)
  ElMessage.success(message ?? '添加成功')
  visibleDrawer.value = false
  props.getArticleList()
}

defineExpose({
  openDrawer,
})
</script>

<style lang="scss" scoped>
.avatar-uploader {
  :deep() {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}
.editor {
  width: 100%;
  :deep(.ql-editor) {
    min-height: 200px;
  }
}
</style>
