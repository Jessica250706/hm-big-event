import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/Login.vue'
import Layout from '@/layout/index.vue'

// 定义路由关系
const routes = [
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/',
    component: Layout,
  },
]

// 创建路由器
const router = createRouter({
  history: createWebHistory(),
  routes: routes,
})

// 导出路由
export default router
