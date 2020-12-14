package com.lizard.web.springboottest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.lizard.web.controller.MainCtrl;
import com.lizard.web.service.UserService;

/**
 * 描述：MockMvc访问带依赖的Controller
 *
 * @author x
 * @since 2020-06-18 21:49
 */
@WebMvcTest(MainCtrl.class)
public class WebMockTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void testWebMockInject() throws Exception {
        Mockito.when(userService.saveOrUpdate()).thenReturn(0);
        MvcResult mvcResult =
            mockMvc.perform(MockMvcRequestBuilders.get("/main/goto")).andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        System.out.println("content = " + content);
    }
}
