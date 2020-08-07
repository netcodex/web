package com.lizard.simpleweb.exception;

import com.lizard.simpleweb.token.CsrfToken;

import java.util.Locale;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-08 0:03
 */
public class InvalidCsrfTokenException extends CsrfTokenException {
    public InvalidCsrfTokenException(CsrfToken expectedToken, String actualToken) {
        super(String.format(Locale.ROOT, "Invalid csrf token '%s' was found on the request header %s or parameter %s.",
            actualToken, expectedToken.getHeaderName(), expectedToken.getParameterName()));
    }
}
