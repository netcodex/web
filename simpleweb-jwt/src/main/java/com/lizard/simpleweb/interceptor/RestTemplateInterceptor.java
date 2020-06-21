package com.lizard.simpleweb.interceptor;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

/**
 * 描述：自定义RestTemplate拦截器 可以对RestTemplate和AsyncRestTemplate发起的请求进行拦截，并在其被发送至服务端之前修改请求或是增强相应的信息。
 *
 * @author x
 * @since 2020-06-21 22:21
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        HttpMethod method = request.getMethod();
        Assert.notNull(method, "HttpMethod can not be null");
        if (method.equals(HttpMethod.POST)) {
            System.out.println("requestURI = " + request.getURI() + ", body = " + Arrays.toString(body));
        }
        return execution.execute(request, body);
    }
}
