package com.lizard.web.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述：响应监听器
 *
 * @author x
 * @since 2020-07-06 22:42
 */
public class ResponseListener implements IWorkListener {
    @Getter
    @Setter
    private WorkProcessor processor;

    @Override
    public BaseResponse onSuccess() {
        return BaseResponse.builder().statusCode(200).message("success").build();
    }

    @Override
    public BaseResponse onFail() {
        return BaseResponse.builder().statusCode(404).message("failed").build();
    }
}
