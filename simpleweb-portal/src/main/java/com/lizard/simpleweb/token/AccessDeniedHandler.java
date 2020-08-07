package com.lizard.simpleweb.token;

import com.lizard.simpleweb.exception.AccessDeniedException;
import com.lizard.simpleweb.utils.constant.WebAttribute;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 23:35
 */
public class AccessDeniedHandler implements AccessHandler {
    private String errorPage;

    public void setErrorPage(String errorPage) {
        if (errorPage != null && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must start with '/'");
        }
        this.errorPage = errorPage;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws ServletException, IOException {
        if (!response.isCommitted()) {
            request.setAttribute(WebAttribute.ACCESS_DENIED, accessDeniedException);
            response.setStatus(HttpStatus.FORBIDDEN.value());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPage);
            requestDispatcher.forward(request, response);
        } else {
            response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
        }
    }
}
