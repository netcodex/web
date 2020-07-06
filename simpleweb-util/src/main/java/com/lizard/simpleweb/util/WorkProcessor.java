package com.lizard.simpleweb.util;

/**
 * 描述：业务处理器
 *
 * @author x
 * @since 2020-07-06 23:09
 */
public class WorkProcessor {

    public BaseResponse doWork(IWorkListener listener) {
        // 业务逻辑
        return listener.onSuccess();
    }

    public static void main(String[] args) {
        WorkProcessor workProcessor = new WorkProcessor();
        BaseResponse baseResponse = new BaseResponse();
        ResponseListener listener = new ResponseListener();
        listener.setResponse(baseResponse);
        listener.setProcessor(workProcessor);
        // BaseResponse response = listener.doRequestWork();
        BaseResponse response = workProcessor.doWork(listener);
        System.out.println("response = " + response);
    }
}
