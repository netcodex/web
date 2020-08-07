package com.lizard.simpleweb.filter;

import com.lizard.simpleweb.exception.InvalidCsrfTokenException;
import com.lizard.simpleweb.exception.MissingCsrfTokenException;
import com.lizard.simpleweb.token.AccessDeniedHandler;
import com.lizard.simpleweb.token.AccessHandler;
import com.lizard.simpleweb.token.CsrfToken;
import com.lizard.simpleweb.token.CsrfTokenRepository;
import com.lizard.simpleweb.token.HttpSessionCsrfTokenRepository;
import com.lizard.simpleweb.token.RequestMatcher;
import com.lizard.simpleweb.utils.UrlUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 描述：Csrf过滤器
 *
 * @author x
 * @since 2020-08-06 23:36
 */
public class CsrfFilter extends OncePerRequestFilter {
    private CsrfTokenRepository tokenRepository;
    private final AccessHandler accessDeniedHandler = new AccessDeniedHandler();
    private final RequestMatcher requestMatcher = new DefaultCsrfRequestMatcher();

    public CsrfFilter(CsrfTokenRepository tokenRepository) {
        Assert.notNull(tokenRepository, "csrfTokenRepository cannot be null");
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        tokenRepository = new HttpSessionCsrfTokenRepository();

        CsrfToken csrfToken = tokenRepository.loadToken(request);
        final boolean missingToken = csrfToken == null;

        if (missingToken) {
            csrfToken = tokenRepository.generateToken();
            tokenRepository.saveToken(csrfToken, request, response);
        }

        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        request.setAttribute(csrfToken.getParameterName(), csrfToken);

        if (requestMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String actualToken = request.getHeader(csrfToken.getHeaderName());
        if (actualToken == null) {
            actualToken = request.getParameter(csrfToken.getParameterName());
        }

        if (!actualToken.equals(csrfToken.getToken())) {
            if (logger.isDebugEnabled()) {
                logger.debug("Invalid csrf token found for " + UrlUtil.buildFullRequestUrl(request));
            }
            if (missingToken) {
                accessDeniedHandler.handle(request, response, new MissingCsrfTokenException(actualToken));
            } else {
                accessDeniedHandler.handle(request, response, new InvalidCsrfTokenException(csrfToken, actualToken));
            }
            return;
        }
        filterChain.doFilter(request, response);
    }

    private static final class DefaultCsrfRequestMatcher implements RequestMatcher {
        private final Set<HttpMethod> allowedMethods =
            new HashSet<>(Arrays.asList(HttpMethod.TRACE, HttpMethod.GET, HttpMethod.HEAD, HttpMethod.OPTIONS));

        @Override
        public boolean matches(HttpServletRequest request) {
            return allowedMethods.contains(HttpMethod.valueOf(request.getMethod()));
        }
    }

}
