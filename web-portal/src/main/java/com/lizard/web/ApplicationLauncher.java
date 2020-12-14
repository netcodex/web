package com.lizard.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lizard.web.mapper")
@SpringBootApplication
public class ApplicationLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

}
