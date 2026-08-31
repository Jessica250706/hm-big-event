package com.xq.interceptors;

import com.xq.utils.JwtUtil;
import com.xq.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 把业务数据存储到 ThreadLocal 中
            ThreadLocalUtil.set(claims);
            return true; // 放行
        } catch (Exception e) {
            // http响应状态码为401
            response.setStatus(401);
            return false; // 拦截
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空 ThreadLocal 中的数据，防止内存泄漏
        ThreadLocalUtil.remove();
    }
}
