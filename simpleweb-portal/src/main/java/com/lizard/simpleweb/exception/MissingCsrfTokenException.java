package com.lizard.simpleweb.exception;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-08 0:00
 */
public class MissingCsrfTokenException extends CsrfTokenException {
    public MissingCsrfTokenException(String actualToken) {
        super("cannot verify Csrf token because session is missing");
    }
}
