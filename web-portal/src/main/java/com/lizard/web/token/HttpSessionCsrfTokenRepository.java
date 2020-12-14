package com.lizard.web.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 描述：默认基于HttpSession实现CsrfToken的管理
 *
 * @author x
 * @since 2020-08-06 23:08
 */
public class HttpSessionCsrfTokenRepository implements CsrfTokenRepository {
    public static final String DEFAULT_CSRF_HEADER_NAME = "X_CSRF_TOKEN";
    public static final String DEFAULT_CSRF_PARAMETER_NAME = "csrf";
    public static final String DEFAULT_CSRF_TOKEN_ATTRIBUTE_NAME = "csrfToken";

    private String headerName = DEFAULT_CSRF_HEADER_NAME;
    private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;
    private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTRIBUTE_NAME;

    @Override
    public CsrfToken generateToken() {
        return new DefaultCsrfToken(headerName, parameterName, createToken());
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        if (csrfToken == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(sessionAttributeName);
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(sessionAttributeName, csrfToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute(sessionAttributeName);
    }

    private String createToken() {
        return UUID.randomUUID().toString();
    }
}
