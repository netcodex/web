package com.lizard.web.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.lizard.web.UserService;
import com.lizard.web.bean.User;
import com.lizard.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 23:12
 */
@DubboService(version = "1.0")
@Qualifier("userServiceImplV1")
public class UserServiceImplV1 implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getList() {
        return userMapper.getUserList();
    }
}
