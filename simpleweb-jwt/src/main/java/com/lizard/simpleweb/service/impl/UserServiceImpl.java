package com.lizard.simpleweb.service.impl;

import org.springframework.stereotype.Service;

import com.lizard.simpleweb.service.UserService;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-16 23:43
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private int index = 10;

    @Override
    public int saveOrUpdate() {
        return 0;
    }
}
