package com.lizard.web.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.lizard.web.controller.SignCtrl;

/**
 * 描述：Web层面的测试
 * <p>
 * 可以使用{@code @WebMvcTest}注解实现不启动服务器，仅在Web层面注入
 * <p>
 * Spring ApplicationContext组件，以下实例可以跟{@link TestingWebApplicationTest}
 * <p>
 * 一样处理MockMvc请求，但要注意的是@WebMvcTest仅仅实例化了WebMvc容器的applicationContext
 * <p>
 * 而不是整个applicationContext，@WebMvcTest甚至支持仅启动指定的Controller：
 * <p>
 * <@code @WebMvcTest(SignCtrl.class)>
 * 
 * @author x
 * @since 2020-06-17 23:00
 */
@WebMvcTest
public class WebLayerTest {
    @Autowired
    ApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testWebMvcMockRequest() throws Exception {
        /*
        这里注入的ApplicationContext只是SpringWebMvc容器的上下文，所以不能加载Spring容器中的Bean
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        userService.saveOrUpdate();
        而加载下面的Controller则加载成功：
        */
        SignCtrl signCtrl = context.getBean("signCtrl", SignCtrl.class);
        System.out.println("signCtrl = " + signCtrl);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/sign/signin"))
            .andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        System.out.println("content = " + content);
    }
}
