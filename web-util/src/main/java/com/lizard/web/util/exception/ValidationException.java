package com.lizard.web.util.exception;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-24 0:17
 */
public class ValidationException extends RuntimeException {

    /**
     * Instantiates a new validation exception.
     */
    protected ValidationException() {
        // hidden
    }

    /**
     * Creates a new instance of ValidationException.
     *
     * @param message the message logged
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ValidationException.
     *
     * @param message the message logged
     * @param cause   the cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
