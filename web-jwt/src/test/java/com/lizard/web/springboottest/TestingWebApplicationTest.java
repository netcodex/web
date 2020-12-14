package com.lizard.web.springboottest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.lizard.web.service.UserService;

/**
 * 描述：MockMvc测试不启动服务器向Controller发送Http请求
 *
 * <p>
 * {@link HttpRequestTest}中相当于启动了一个完整的服务器，
 * <P>
 * 另外有一种根本不启动服务器，仅在Web层面测试处理传入的HTTP请求并映射到Controller。
 * <P>
 * 也即使用Spring的MockMVC
 * <p>
 * 使用{@code @AutoConfigureMockMvc}注解要求自动配置注入MockMVC
 *
 * @author x
 * @since 2020-06-17 22:23
 */

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * mockMva#perform方法要求传入一个RequestBuilder请求构造器并返回一个ResultActions，
     * <p>
     * ResultActions有三个方法：
     * <ul>
     * <li>andExpect(ResultMatcher var1) 添加一个结果期望值匹配器
     * <li>andDo(ResultHandler var1) 添加一个结果处理器
     * <li>andReturn() 返回一个MvcResult
     * </ul>
     */
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.saveOrUpdate();
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/sign/signin"))
            .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(containsString("get request and response OK! ")))
            .andReturn();
    }
}
