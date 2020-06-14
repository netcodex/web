package com.lizard.simpleweb;

import java.util.Base64;

import org.junit.jupiter.api.Test;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-14 22:48
 */
public class JWTTest {
    @Test
    public void testJWT() {
        String s = Base64.getEncoder().encodeToString("abcde".getBytes());// YXBpYw==
        System.out.println("s = " + s);
    }
}
