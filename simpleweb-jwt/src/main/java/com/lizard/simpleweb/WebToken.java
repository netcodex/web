package com.lizard.simpleweb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    private String secret;
    private String issuer;
    private int expireTime;
}
