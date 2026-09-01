package com.xq.service.impl;

import com.xq.mapper.UserMapper;
import com.xq.pojo.User;
import com.xq.service.UserService;
import com.xq.utils.Md5Util;
import com.xq.utils.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        // 加密密码
        String md5String = Md5Util.getMD5String(password);
        // 注册
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Integer id = UserContextUtil.getCurrentUserId();
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Integer id = UserContextUtil.getCurrentUserId();
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
    }
}
