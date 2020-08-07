package com.lizard.simpleweb.exception;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 23:33
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
