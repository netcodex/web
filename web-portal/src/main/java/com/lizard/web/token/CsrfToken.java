package com.lizard.web.token;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-06 22:47
 */
public interface CsrfToken extends Serializable {
    String getHeaderName();

    String getParameterName();

    String getToken();
}
