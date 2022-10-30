package com.lizard.web.config;

import com.lizard.web.UserService;
import org.apache.dubbo.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author X
 * @version 1.0
 * @since 2022-10-26 19:48
 **/
@Configuration
@ImportResource("classpath:provider.xml")
public class DubboConfig {

    /**
     * 指定应用名等应用级别相关信息,一个应用内只允许出现一个,必选
     * 
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig appConfig = new ApplicationConfig();
        // 当前应用名称，用于注册中心计算应用间依赖关系
        appConfig.setName("portal");
        return appConfig;
    }

    /**
     * 要暴露的 RPC 协议及相关配置如端口号等，一个应用可配置多个，一个 protocol 可作用于一组 service&reference，可选，默认 dubbo
     *  如果需要支持多协议，可以声明多个 <dubbo:protocol> 标签，并在 <dubbo:service> 中通过 protocol 属性指定使用的协议。
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        // 协议名称
        protocolConfig.setName("dubbo");
        // 服务端口
        protocolConfig.setPort(6080);
        return protocolConfig;
    }

    /**
     * 注册中心类型、地址及相关配置
     * 一个应用内可配置多个，一个 registry 可作用于一组 service&reference	必选
     *
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        // 注册中心引用BeanId，可以在<dubbo:service registry="">或<dubbo:reference registry="">中引用此ID
        registryConfig.setId("web-registry");
        // 注册中心服务器地址，如果地址没有端口缺省为9090，同一集群内的多个地址用逗号分隔，如：ip:port,ip:port，不同集群的注册中心，请配置多个<dubbo:registry>标签
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        return registryConfig;
    }
}
