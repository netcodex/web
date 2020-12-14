package com.lizard.web.util;

/**
 * 描述：回调函数接口
 *
 * @author x
 * @since 2020-07-06 22:39
 */
public interface IWorkListener {
    BaseResponse onSuccess();

    BaseResponse onFail();
}
