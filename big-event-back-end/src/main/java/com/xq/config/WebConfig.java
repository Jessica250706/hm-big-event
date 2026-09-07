package com.xq.config;

import com.xq.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录和注册接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/api/user/login", "/api/user/register");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 给所有 Controller 方法添加 /api 前缀（条件始终为 true）
        configurer.addPathPrefix("/api", c -> true);
    }
}
