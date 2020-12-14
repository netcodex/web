package com.lizard.web.token;

import com.lizard.web.exception.AccessDeniedException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 23:31
 */
public interface AccessHandler {
    void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws ServletException, IOException;
}
