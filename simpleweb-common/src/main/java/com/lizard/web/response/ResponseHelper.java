package com.lizard.web.response;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-10 23:03
 */
public class ResponseHelper {
    public static Response success() {
        return success("success", null);
    }

    public static Response success(String message) {
        return success(message, null);
    }

    public static <T> Response success(String message, T data) {
        return build().fail(false).code(200).message(message).data(data);
    }

    public static Response fail() {
        return fail("fail", null);
    }

    public static Response fail(String message) {
        return fail(message, null);
    }

    public static <T> Response fail(String message, T data) {
        return fail(404, message, data);
    }

    public static <T> Response fail(int code, String message, T data) {
        return build().fail(true).code(code).message(message).data(data);
    }

    public static Response build() {
        return new Response();
    }

    public static Response notFound() {
        return fail(404, "not found", null);
    }

    public static Response internalError() {
        return fail(500, "internal error", null);
    }
}
