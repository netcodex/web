package com.lizard.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.lizard.web.util.JwtHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：JWT过滤器
 *
 * @author x
 * @since 2020-06-14 17:43
 */
@Slf4j
public class JwtFilter implements Filter {
    // X-Auth-Token: Bearer <token>
    private static final String X_AUTH_REFER = "X-Auth-Token";
    private static final String X_AUTH_PREFIX = "Bearer";

    @Value("${webtoken.base64Secret}")
    private String base64Secret;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String method = request.getMethod();
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (HttpMethod.OPTIONS.matches(method)) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }
        String header = request.getHeader(X_AUTH_REFER);
        if (StringUtils.isEmpty(header) || !header.startsWith(X_AUTH_PREFIX)) {
            log.info("HEADER: {} , Auth failed.", header);
            throw new ServletException("Auth failed");
        }
        String token = header.substring(7);
        JwtHelper.parseJWT(token, base64Secret);
        filterChain.doFilter(request, response);
    }

}
