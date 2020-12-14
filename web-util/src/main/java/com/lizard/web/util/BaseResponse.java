package com.lizard.web.util;

import lombok.Data;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-06 22:50
 */
@Data
public class BaseResponse {
    private int httpStatusCode;
    private String message;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int httpStatusCode;
        private String message;

        public Builder statusCode(int statusCode) {
            this.httpStatusCode = statusCode;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public BaseResponse build() {
            BaseResponse response = new BaseResponse();
            response.setHttpStatusCode(httpStatusCode);
            response.setMessage(message);
            return response;
        }

    }
}
