package com.xq.service.impl;

import com.xq.mapper.UserMapper;
import com.xq.pojo.User;
import com.xq.service.UserService;
import com.xq.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        // 加密密码
        String md5String = Md5Util.getMD5String(password);
        // 注册
        userMapper.add(username, password);
    }
}
