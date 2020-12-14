package com.lizard.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationLauncherTests {

    @Autowired
    private Person Person;

    @Test
    void contextLoads() {
        System.out.println("Person = " + Person);
    }

}
