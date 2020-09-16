package com.lizard.simpleweb;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：自定义RestTemplate代理 为除了指定主机之外的所有主机设置代理
 *
 * @author x
 * @since 2020-06-21 22:59
 */
public class CustomHttpClientProxy implements RestTemplateCustomizer {
    public static final String PROXY = "proxy.huawei.com";
    public static final String EXCLUSION = "10.0.0.1";

    @Override
    public void customize(RestTemplate restTemplate) {
        HttpHost proxy = new HttpHost(PROXY);
        HttpClient client =
                HttpClientBuilder.create()
                        .setRoutePlanner(
                                new DefaultProxyRoutePlanner(proxy) {
                                    @Override
                                    protected HttpHost determineProxy(
                                            HttpHost target,
                                            HttpRequest request,
                                            HttpContext context)
                                            throws HttpException {
                                        if (target.getHostName().equals(EXCLUSION)) {
                                            return null;
                                        }
                                        return super.determineProxy(target, request, context);
                                    }
                                })
                        .build();
    }
}
