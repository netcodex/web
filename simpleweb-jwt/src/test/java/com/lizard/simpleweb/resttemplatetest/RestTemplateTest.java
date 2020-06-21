package com.lizard.simpleweb.resttemplatetest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import com.alibaba.fastjson.JSON;
import com.lizard.simpleweb.model.ApicUser;

/**
 * 描述：测试RestTemplate
 *
 * @author x
 * @since 2020-06-21 0:24
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RestTemplateTest {
    private static final String LOCALURL = "http://localhost:8080/sign/user";
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testGetForObject() {
        ApicUser user = this.testRestTemplate.getForObject(LOCALURL, ApicUser.class);
        System.out.println("user = " + user);
    }

    @Test
    public void testGetForEntity() {
        ResponseEntity<ApicUser> resp = this.testRestTemplate.getForEntity(LOCALURL, ApicUser.class);
        HttpStatus statusCode = resp.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        HttpHeaders headers = resp.getHeaders();
        System.out.println("headers = " + headers);
        ApicUser body = resp.getBody();
        System.out.println("body = " + body);
    }

    /**
     * {@link org.springframework.web.client.RestTemplate#postForEntity(String, Object, Class, Object...)}的参数：
     * <p>
     * {@code @param String }url路径
     * <p>
     * {@code @param Object }表单参数，一般由{@link MultiValueMap}封装
     * <p>
     * {@code @param Class }返回结果封装JavaBean类型
     * <p>
     * {@code @param Object }Url参数
     */
    @Test
    public void testPostForEntity() {
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<>();
        ResponseEntity<String> resp =
            testRestTemplate.postForEntity(LOCALURL, requestParams, String.class, "name=alex");
        HttpStatus statusCode = resp.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        String body = resp.getBody();
        System.out.println("body = " + body);
        HttpHeaders headers = resp.getHeaders();
        System.out.println("headers = " + headers);
    }

    /**
     * POST请求，结合表单参数和Url参数
     */
    @Test
    public void testPostForObject() {
        /**
         * public interface MultiValueMap<K, V> extends Map<K, List<V>>
         * <p>
         * 注意：MultiValueMap键值对中的值为List类型的集合
         */
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("prod", "wechat");
        ResponseEntity<String> resp =
            testRestTemplate.postForEntity(LOCALURL + "/1/?username={0}", requestParams, String.class, "alex");
        HttpStatus statusCode = resp.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        String body = resp.getBody();
        System.out.println("body = " + body);
        HttpHeaders headers = resp.getHeaders();
        System.out.println("headers = " + headers);
    }

    @Test
    public void testExchange() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        // 如果传入的是URL参数拼接格式的，则MediaType应该指定为“application/x-www-form-urlencoded”，不然requestParam取不到对应的参数
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        String msgBody = String.format(Locale.ROOT, "age=%d&name=%s&email=%s", 24, "bob", "bob@gmail.com");
        HttpEntity<String> httpEntity = new HttpEntity<>(msgBody, headers);
        ResponseEntity<ApicUser> resp =
            testRestTemplate.exchange(LOCALURL + "1?gender={0}", HttpMethod.POST, httpEntity, ApicUser.class, "male");
        printResponseResult(resp);

        // 以MutiValueMap封装参数
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("age", "26");
        body.add("name", "Tom");
        body.add("email", "tom@gmail.com");
        HttpEntity<MultiValueMap<String, String>> httpEntity1 = new HttpEntity<>(body, headers);
        resp =
            testRestTemplate.exchange(LOCALURL + "1?gender={0}", HttpMethod.POST, httpEntity1, ApicUser.class, "male");
        printResponseResult(resp);

    }

    private void printResponseResult(ResponseEntity<ApicUser> resp) {
        HttpStatus statusCode = resp.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        ApicUser body = resp.getBody();
        System.out.println("body = " + body);
        HttpHeaders respHeaders = resp.getHeaders();
        System.out.println("headers = " + respHeaders);
    }

    @Test
    public void testExecute() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE,
            Arrays.asList(MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE));
        // 如果传入的是Json格式的字符串，则Ctrl应该以@RequestBody接收
        String userJsonStr = JSON.toJSONString(new ApicUser("bob", "bob@gmail.com"));
        System.out.println("userJsonStr = " + userJsonStr);
        HttpEntity<String> httpEntity = new HttpEntity<>(userJsonStr, headers);

        RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void doWithRequest(ClientHttpRequest request) throws IOException {}
        };

        ResponseExtractor<ApicUser> responseExtractor = new ResponseExtractor<ApicUser>() {
            @Override
            public ApicUser extractData(ClientHttpResponse response) throws IOException {
                return null;
            }
        };

        ApicUser resp = testRestTemplate.execute(LOCALURL + "2?gender={0}", HttpMethod.POST, requestCallback,
            responseExtractor, "male");

        System.out.println("resp = " + resp);
    }
}
