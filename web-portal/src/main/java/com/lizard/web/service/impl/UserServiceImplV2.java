package com.lizard.web.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.lizard.web.UserService;
import com.lizard.web.bean.User;
import com.lizard.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 描述：多版本接口
 *
 * @author x
 * @since 2020-08-07 23:12
 */
@DubboService(version = "2.0")
@Qualifier("userServiceImplV2")
public class UserServiceImplV2 implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getList() {
        User user = userMapper.getUserById(1);
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList(user);
    }
}
