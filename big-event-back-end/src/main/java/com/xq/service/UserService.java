package com.xq.service;

import com.xq.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册
    void register(String username, String password);
}
