package com.lizard.simpleweb;

import java.util.Arrays;

import javax.servlet.Filter;

import com.lizard.simpleweb.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-14 17:40
 */
@Configuration
public class WebMVConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<Filter> addJwtFilter() {
        FilterRegistrationBean<Filter> register = new FilterRegistrationBean<>();
        register.setFilter(new JwtFilter());
        register.setUrlPatterns(Arrays.asList("/login", "/main"));
        return register;
    }

}
