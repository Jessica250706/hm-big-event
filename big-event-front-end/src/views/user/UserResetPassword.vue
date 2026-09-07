<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>重置密码</span>
      </div>
    </template>
    <!-- 表单 -->
    <el-row>
      <el-col :span="12">
        <el-form :model="pwdData" label-width="100px" :rules="rules">
          <el-form-item label="原密码" prop="oldPwd">
            <el-input
              v-model="pwdData.oldPwd"
              placeholder="请输入原密码"
              type="password"
              show-password
            />
          </el-form-item>
          <el-form-item label="新密码" prop="newPwd">
            <el-input
              v-model="pwdData.newPwd"
              placeholder="请输入新密码"
              type="password"
              show-password
            />
          </el-form-item>
          <el-form-item label="确认新密码" prop="rePwd">
            <el-input
              v-model="pwdData.rePwd"
              placeholder="请输入确认密码"
              type="password"
              show-password
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="resetPassword">修改密码</el-button>
            <el-button type="primary" plain @click="resetPwdData">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ElMessage, type FormRules } from 'element-plus'
import { updateUserPasswordService } from '@/api/user'
import { useRouter } from 'vue-router'
import { useTokenStore } from '@/stores/token'
import type { pwdDataDTO } from '@/api/user'

const router = useRouter()
const tokenStore = useTokenStore()

const initialPwdData = {
  oldPwd: '',
  newPwd: '',
  rePwd: '',
}
const pwdData = ref<pwdDataDTO>({ ...initialPwdData })

const rules = reactive<FormRules>({
  oldPwd: [
    { required: true, message: '请输入原密码', trigger: 'blur' },
    { pattern: /^\S{5,16}$/, message: '原密码必须是5-16位的非空字符串', trigger: 'blur' },
  ],
  newPwd: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { pattern: /^\S{5,16}$/, message: '新密码必须是5-16位的非空字符串', trigger: 'blur' },
  ],
  rePwd: [
    { required: true, message: '请输入确认密码', trigger: 'blur' },
    { pattern: /^\S{5,16}$/, message: '确认密码必须是5-16位的非空字符串', trigger: 'blur' },
  ],
})

const resetPwdData = () => {
  pwdData.value = { ...initialPwdData }
}

const resetPassword = async () => {
  await updateUserPasswordService(pwdData.value)
  resetPwdData()
  tokenStore.removeToken()
  ElMessage.success('修改成功，请重新登录')
  router.push('/login')
}
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
