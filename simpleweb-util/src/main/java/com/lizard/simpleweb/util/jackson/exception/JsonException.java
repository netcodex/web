package com.lizard.simpleweb.util.jackson.exception;

import java.util.Locale;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-12 1:23
 */
public class JsonException extends RuntimeException {
    public JsonException() {
    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Object... arguments) {
        super(message);
    }
}
