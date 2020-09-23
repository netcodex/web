package com.lizard.simpleweb.util;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-23 23:13
 */
public class SsrfUtil {

    /**
     * Not allowed 302 redirection
     * Not allowed scheme out of HTTP and HTTPS
     * No CR-LF Injection in faraday
     * Only POST method
     * <p>
     * Blacklist localhost, 127.0.0.1… ETC
     * WhiteList 172.31. 172.35. ...
     *
     * @param url
     * @return
     */
    public static boolean isUrlValid(String url) {
        // 检查IP
        // 检查域名
        return false;
    }
}
