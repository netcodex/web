package com.lizard.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述：Swagger配置
 *
 * @author x
 * @since 2020-06-28 21:46
 */
@Configuration
@EnableSwagger2 // 启用swagger文档，此注解也可以加在引导类，如果没有该注解，swagger文档将不会生效
public class SwaggerConfig {
    /**
     * 基于swagger2.0规范初始化的Springfox的主API配置机制
     *
     * @return Docket Springfox’s, primary api configuration mechanism is initialized for swagger specification 2.0
     */
    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            /*
             * select() returns an instance of ApiSelectorBuilder to give fine grained control over the endpoints
             * exposed via swagger.
             */
            .select()
            /*
             * apis() allows selection of RequestHandler's using a predicate. The example here uses an any predicate
             * (default). Out of the box predicates provided are any, none, withClassAnnotation, withMethodAnnotation
             * and basePackage.
             */
            .apis(RequestHandlerSelectors.basePackage("com.lizard.web.controller"))
            // API路径
            .paths(PathSelectors.any()).build();
    }

    /**
     * 注册一个API接口
     * 
     * @return ApiInfo
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 标题
            .title("测试探测服务接口").description("探测网关服务")
            // 目标服务地址
            .termsOfServiceUrl("http://localhost:8080/swagger").version("1.0").build();
    }
}
