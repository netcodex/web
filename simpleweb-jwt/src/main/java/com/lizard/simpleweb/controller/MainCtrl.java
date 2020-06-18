package com.lizard.simpleweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizard.simpleweb.service.UserService;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-18 21:58
 */
@RestController
@RequestMapping("/main")
public class MainCtrl {
    private final UserService userService;

    public MainCtrl(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/goto")
    public void gotoMainFrame() {
        userService.saveOrUpdate();
    }
}
