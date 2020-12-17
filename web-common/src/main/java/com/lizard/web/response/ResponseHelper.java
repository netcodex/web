package com.lizard.web.response;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-10 23:03
 */
public class ResponseHelper {
    public static Response ok() {
        return ok("success", null);
    }

    public static Response ok(String message) {
        return ok(message, null);
    }

    public static <T> Response ok(String message, T data) {
        return success(200, message, data);
    }

    public static <T> Response success(int code, String message, T data) {
        return build().fail(false).code(code).message(message).data(data);
    }

    public static Response badRequest() {
        return badRequest("fail", null);
    }

    public static Response badRequest(String message) {
        return badRequest(message, null);
    }

    public static <T> Response badRequest(String message, T data) {
        return fail(400, message, data);
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
