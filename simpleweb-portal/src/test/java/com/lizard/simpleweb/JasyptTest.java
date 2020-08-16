package com.lizard.simpleweb;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-16 19:07
 */
public class JasyptTest {
    @Test
    public void testEncrypt() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // 加密所需的salt(盐)
        encryptor.setPassword("oZ9tV2nL3ETcOODJEVqEbA==");
        // 要加密的数据（数据库的用户名或密码）
        String username = encryptor.encrypt("scott");
        String password = encryptor.encrypt("tiger");
        System.out.println("username:" + username);
        System.out.println("password:" + password);

        String decryptUsername = encryptor.decrypt(username);
        String decryptPassword = encryptor.decrypt(password);
        System.out.println("decryptUsername = " + decryptUsername);
        System.out.println("decryptPassword = " + decryptPassword);
    }
}
