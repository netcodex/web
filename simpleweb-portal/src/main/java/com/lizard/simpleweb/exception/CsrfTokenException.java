package com.lizard.simpleweb.exception;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 23:59
 */
public class CsrfTokenException extends AccessDeniedException {
    public CsrfTokenException(String message) {
        super(message);
    }
}
