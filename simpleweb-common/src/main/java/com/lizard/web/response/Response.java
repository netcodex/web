package com.lizard.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-10 23:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private boolean fail;
    private int code;
    private String message;
    private Object data;

    public Response code(int httpCode) {
        this.code = httpCode;
        return this;
    }

    public Response message(String message) {
        this.message = message;
        return this;
    }

    public Response data(Object data) {
        this.data = data;
        return this;
    }

    public Response fail(boolean fail) {
        this.fail = fail;
        return this;
    }
}
