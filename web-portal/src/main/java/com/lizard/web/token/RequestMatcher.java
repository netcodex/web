package com.lizard.web.token;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-08 0:58
 */
public interface RequestMatcher {
    boolean matches(HttpServletRequest request);
}
