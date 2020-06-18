package com.lizard.simpleweb.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * 描述：模拟启动一个完整运行的web服务器并发送HTTP请求
 *
 * @author x
 * @since 2020-06-17 21:09
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    /**
     * {@link LocalServerPort} 注解，注入一个测试用例实际使用的服务器端口
     */
    @LocalServerPort
    private int port;
    /**
     * SpringBootTest还自动提供了一个TestRestTemplate用于发起REST调用到启动服务器的的测试
     */
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testHttpRequest() {
        String s = this.testRestTemplate.getForObject("http://localhost:8080/sign/signin", String.class);
        System.out.println("s = " + s);
        System.out.println("port = " + port);
    }
}
