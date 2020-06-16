package com.lizard.simpleweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.lizard.simpleweb.controller.SignCtrl;
import com.lizard.simpleweb.service.UserService;
import com.lizard.simpleweb.service.impl.UserServiceImpl;

/**
 * 描述： {@code @ContextConfiguration(classes=…)} SpringTest框架中的注解：指定加载哪些Spring @Configuration
 * {@code @SpringBootTest}可以配置哪些参数？ <ui>
 * <li>classes：指定SpringBoot的启动类
 * <li>WebEnvironment=WebEnvironment.RANDOM_PORT 使用随机端口启动完整的运行服务器 </ui>
 *
 * @author x
 * @since 2020-06-16 22:00
 */
@SpringBootTest
public class SpringBootTestFrameworkTest {
    @Autowired
    SignCtrl signCtrl;
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testContextLoad() {
        Assert.notNull(signCtrl, "signCtrl can not be null");
        UserService userService = applicationContext.getBean("userService", UserServiceImpl.class);
        System.out.println("userService.getClass() = " + userService.getClass());
        System.out.println("userService = " + userService.saveOrUpdate());
    }

}
