package com.lizard.simpleweb.util.jackson.exception;

import com.lizard.simpleweb.util.exception.ParameterFormatter;

/**
 * 描述：Json异常类
 *
 * @author x
 * @since 2020-09-12 1:23
 */
public class JsonException extends RuntimeException {
    public JsonException(String message) {
        super(message);
    }

    public JsonException(String format, Object... arguments) {
        super(ParameterFormatter.format(format, arguments));
    }
}
