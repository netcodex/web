package com.lizard.simpleweb;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lizard.simpleweb.filter.JwtFilter;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-14 17:40
 */
@Configuration
public class WebMVConfig implements WebMvcConfigurer {

    /**
     * 注册登陆过滤器
     * <p>
     * SpringBoot中配置Servlet：{@link org.springframework.boot.web.servlet.ServletRegistrationBean}
     * <p>
     * Listener：{@link org.springframework.boot.web.servlet.ServletListenerRegistrationBean}也是同样的方式
     */
    @Bean
    public FilterRegistrationBean<Filter> addJwtFilter() {
        FilterRegistrationBean<Filter> register = new FilterRegistrationBean<>();
        register.setFilter(new JwtFilter());
        register.setUrlPatterns(Arrays.asList("/login", "/main"));
        return register;
    }

    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowCredentials(true).allowedOrigins("*").maxAge(10 * 60 * 60);
    }

}
