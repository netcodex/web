package com.lizard.web.service;

import org.springframework.stereotype.Service;

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
        System.out.println("UserServiceImpl.saveOrUpdate……");
        return 0;
    }
}
