package com.lizard.simpleweb.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author x
 * Date: 2020-06-06 16:29
 */
@RestController
public class HelloContoller {
    @GetMapping("/hello")
    public void hello() {
        System.out.println("helo!");
    }

    @GetMapping("/hello/{userid}")
    public void getUserId(@PathVariable("userid") String userid) {
        System.out.println("getHello!" + userid);
    }

    @GetMapping("/hello/{userid}")
    public void getMapInfo(@PathVariable("userid") String userid) {
        System.out.println("getHello!" + userid);
    }

    @GetMapping("/hello/{userid}")
    public void getPojoInfo(@PathVariable("userid") String userid) {
        System.out.println("getHello!" + userid);
    }
}
