package com.lizard.web.controller;

import com.lizard.web.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.lizard.web.bean.User;
import com.lizard.web.mapper.UserMapper;

import io.swagger.annotations.Api;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Resource(name = "userServiceImplV1")
    private UserService userService;

    @GetMapping("/name/{id}")
    public String getUserName(@PathVariable("id") Integer id) {
        String name = userMapper.getName(id);
        System.out.println("name = " + name); // null
        return name;
    }

    @GetMapping("/age/{id}")
    public Integer getUserAge(@PathVariable("id") Integer id) {
        Integer age = userMapper.getAge(id);
        System.out.println("age = " + age); // null
        return age;
    }

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") Integer id) {
        User user = userMapper.getUserById(id);
        System.out.println("user = " + user); // null
        return user;
    }

    @GetMapping("/list")
    public List<User> getUserList() {
        List<User> userList = userMapper.getUserList();
        System.out.println("userList = " + userList); // []
        return userList;
    }

    @GetMapping("/set")
    public Set<User> getUserSet() {
        Set<User> set = userMapper.getUserSet();
        System.out.println("set = " + set); // []
        return set;
    }

    @GetMapping("/map")
    public Map<String, Integer> getUserMap() {
        Map<String, Integer> nameAgeMap = userMapper.getNameAgeMap();
        System.out.println("nameAgeMap = " + nameAgeMap); // null
        return nameAgeMap;
    }

    @PostMapping("")
    public int insertUser() {
        User user = new User();
        user.setName("scott");
        user.setAge(24);
        user.setEmail("scott@gmail.com");
        user.setGender("male");
        return userMapper.insert(user);
    }

    @GetMapping("/getDubboUserList")
    public List<User> getDubboUserList() {
        return userService.getList();
    }
}
