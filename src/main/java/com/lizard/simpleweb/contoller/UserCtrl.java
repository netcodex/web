package com.lizard.simpleweb.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author x
 * Date: 2020-05-21 16:18
 */

@RestController
@RequestMapping("/user")
public class UserCtrl {

    @GetMapping
    public void getUser() {

    }

    @GetMapping("/getmapInfo")
    public void getParamMap(Map<String, Object> map) {
        System.out.println("map = " + map);
    }
}
