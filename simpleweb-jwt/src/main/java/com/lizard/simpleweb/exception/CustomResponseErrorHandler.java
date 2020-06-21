package com.lizard.simpleweb.exception;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * 描述：自定义ResponseErrorHandler ResponseErrorHandler 接口定义了当ClientHttpResponse发生错误时需要进行的操作
 * 
 * @author x
 * @since 2020-06-21 22:39
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpHeaders headers = response.getHeaders();
        return !headers.isEmpty() && response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is5xxServerError()) {
            String statusText = response.getStatusText();
            System.out.println("statusText = " + statusText);
        }
    }
}
