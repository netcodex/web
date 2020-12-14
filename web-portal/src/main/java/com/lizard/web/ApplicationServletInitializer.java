package com.lizard.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 描述：SpringBootServletInitializer 是 WebApplicationInitializer 的实现，
 * <p>
 * 它从部署在 Web 容器上的传统 WAR 包运行 Spring Boot 应用。
 * <p>
 * 该类将 Servlet，Filter 和 ServletContextInitializer Bean 从应用程序上下文绑定到服务器。
 *
 * @author x
 * @since 2020-07-01 23:05
 */
public class ApplicationServletInitializer extends SpringBootServletInitializer {
    /**
     * SpringBootServletInitializer 类还允许我们通过覆盖
     * <p>
     * {@link SpringBootServletInitializer#configure(SpringApplicationBuilder)}
     * <p>
     * 方法来配置由 Servlet 容器运行的应用程序。
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationLauncher.class);
    }
}
