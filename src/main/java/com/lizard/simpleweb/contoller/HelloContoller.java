package com.lizard.simpleweb.contoller;

import org.springframework.web.bind.annotation.GetMapping;
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
}
