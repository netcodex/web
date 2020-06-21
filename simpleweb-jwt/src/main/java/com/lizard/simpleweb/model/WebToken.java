package com.lizard.simpleweb.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-13 12:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "webtoken")
public class WebToken {
    private String clientId;
    @Value("${webtoken.base64Secret}")
    private String secret;
    private String issuer;
    private int expireTime;
}
