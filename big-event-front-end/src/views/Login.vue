<template>
  <el-row class="login-page">
    <!-- 左侧 -->
    <el-col :span="12" class="bg"></el-col>
    <!-- 右侧 -->
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册 -->
      <el-form v-if="isRegister" ref="form" size="large" :model="registerData" :rules="rules">
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="请输入用户名"
            v-model="registerData.username"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            :prefix-icon="Lock"
            placeholder="请输入密码"
            type="password"
            show-password
            v-model="registerData.password"
          />
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input
            :prefix-icon="Lock"
            placeholder="请再次输入密码"
            type="password"
            show-password
            v-model="registerData.rePassword"
          />
        </el-form-item>
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="register">
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = false">← 返回</el-link>
        </el-form-item>
      </el-form>
      <!-- 登录 -->
      <el-form v-if="!isRegister" ref="form" size="large" :model="loginData" :rules="rules">
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="loginData.username" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            :prefix-icon="Lock"
            placeholder="请输入密码"
            type="password"
            show-password
            v-model="loginData.password"
          />
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = true">注册 →</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormRules } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { userRegisterService, userLoginService } from '@/api/user'

interface RegisterData {
  username: string
  password: string
  rePassword: string
}
interface LoginData {
  username: string
  password: string
}

// 控制注册与登录表单的显示，默认显示登录
const isRegister = ref(false)
const registerData = reactive<RegisterData>({
  username: '',
  password: '',
  rePassword: '',
})
const loginData = reactive<LoginData>({
  username: '',
  password: '',
})

function checkRePassword(rule: any, value: any, callback: any) {
  if (value === '') {
    callback(new Error('请再次确认密码'))
  } else if (value !== registerData.password) {
    callback(new Error('请确保两次输入的密码一致'))
  } else {
    callback()
  }
}

// 定义表单校验规则
const rules = reactive<FormRules<RegisterData>>({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' },
  ],
  rePassword: [{ validator: checkRePassword, trigger: 'blur' }],
})

// 调用后台接口，完成注册
async function register() {
  const result = await userRegisterService(registerData)
  ElMessage.success(result.message ? result.message : '注册成功')
}

// 调用后台接口，完成登录
async function login() {
  const result = await userLoginService(loginData)
  ElMessage.success(result.message ? result.message : '登录成功')
}
</script>

<style lang="scss" scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background:
      url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
      url('@/assets/login_bg.jpg') no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>
