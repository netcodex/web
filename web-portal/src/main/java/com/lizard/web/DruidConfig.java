package com.lizard.web;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 描述：Druid数据源配置
 *
 * @author x
 * @since 2020-08-16 17:04
 */
@Configuration
@PropertySource("classpath:application-druid.properties")
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }
}
