package com.xq.utils;

import java.util.Map;

public class UserContextUtil {

    /**
     * 获取当前登录用户的 ID
     * @return 用户ID
     * @throws RuntimeException 如果上下文不存在或用户未登录
     */
    public static Integer getCurrentUserId() {
        Map<String, Object> map = ThreadLocalUtil.get();
        if (map == null) {
            throw new RuntimeException("未获取到用户上下文，请检查登录拦截器");
        }
        Object userId = map.get("id");
        if (userId == null) {
            throw new RuntimeException("用户未登录或登录信息已失效");
        }
        return (Integer) userId;
    }
}
