package com.xq.controller;

import com.xq.pojo.Result;
import com.xq.pojo.User;
import com.xq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        // 查询用户（是否占用）
        User user = userService.findByUserName(username);
        if (user == null) {
            // 没有占用，进行注册
            userService.register(username, password);
            return Result.success();
        } else {
            // 占用
            return Result.error("用户名已被占用");
        }
    }
}
