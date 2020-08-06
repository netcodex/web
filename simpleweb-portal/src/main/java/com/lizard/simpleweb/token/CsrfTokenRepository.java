package com.lizard.simpleweb.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-06 22:55
 */
public interface CsrfTokenRepository {
    CsrfToken generateToken();

    void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response);

    CsrfToken loadToken(HttpServletRequest request);
}
