package com.lizard.simpleweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-15 23:38
 */
@RestController
@RequestMapping("/sign")
public class SignCtrl {
    @RequestMapping("/signin")
    public void signin() {

    }
}
