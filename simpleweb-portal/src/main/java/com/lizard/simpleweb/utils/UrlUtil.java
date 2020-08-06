package com.lizard.simpleweb.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 0:08
 */
public class UrlUtil {
    /**
     * 构建完整请求路径
     *
     * @param request HttpServletRequest
     * @return 完整请求路径
     */
    public static String buildFullRequestUrl(HttpServletRequest request) {
        String schema = request.getScheme().toLowerCase();
        StringBuilder url = new StringBuilder(schema);

        url.append("://").append(request.getServerName());

        int serverPort = request.getServerPort();
        if ("http".equals(schema)) {
            if (80 != serverPort) {
                url.append(":").append(serverPort);
            }
        } else if ("https".equals(schema)) {
            if (443 != serverPort) {
                url.append(":").append(serverPort);
            }
        }
        url.append(request.getRequestURI());

        String queryString = request.getQueryString();
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
