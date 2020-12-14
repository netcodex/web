package com.lizard.web.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.lizard.web.controller.SignCtrl;
import com.lizard.web.service.UserService;

/**
 * 描述：SpringBootTest测试类
 * <p>
 * {@code @ContextConfiguration(classes=…)} 是SpringTest框架中的注解，指定加载哪些Spring @Configuration
 * <p>
 * {@code @SpringBootTest}可以配置哪些参数？ <ui>
 * <li>classes：指定SpringBoot的启动类，如{@code @SpringBootTest(classes = ApplicationStarter.class)}
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

    /**
     * SpringTest框架支持在多个测试方法中共享applicationContext，
     * <p>
     * 只需要使用@DirtiesContext注解即可，Spring会缓存applicationContext
     */
    @Test
    public void testContextLoad() {
        Assert.notNull(signCtrl, "signCtrl can not be null");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("userService.getClass() = " + userService.getClass());// com.lizard.web.service.impl.UserServiceImpl
        System.out.println("userService = " + userService.saveOrUpdate());
    }

}
