package com.lizard.simpleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizard.simpleweb.bean.User;
import com.lizard.simpleweb.mapper.UserMapper;

import io.swagger.annotations.Api;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-02 23:59
 */
@RestController
@Api(value = "用户管理", tags = "用户管理")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    public User getUserInfo() {
        User user = new User();
        user.setName("scott");
        user.setAge(24);
        user.setEmail("scott@gmail.com");
        user.setGender("male");
        int id = userMapper.insert(user);
        System.out.println("id = " + id);
        return userMapper.getUserById(id);
    }
}
