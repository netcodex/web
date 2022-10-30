package com.lizard.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author X
 * @version 1.0
 * @since 2022-10-26 21:25
 **/
@SpringBootApplication
@EnableDubbo
public class ControllerLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ControllerLauncher.class, args);
    }
}
