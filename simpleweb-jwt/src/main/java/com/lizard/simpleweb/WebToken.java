package com.lizard.simpleweb;

import lombok.Data;

/**
 * 描述：
 *
 * @author x
 * @since 2020-06-13 12:07
 */
@Data
public class WebToken {
    private String clientId;
    private String secret;
    private String issuer;
    private int expireTime;
}
