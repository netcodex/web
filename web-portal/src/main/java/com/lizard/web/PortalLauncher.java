package com.lizard.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author X
 */
@MapperScan("com.lizard.web.mapper")
@SpringBootApplication
@EnableDubbo
public class PortalLauncher {
    public static void main(String[] args) {
        SpringApplication.run(PortalLauncher.class, args);
    }
}
