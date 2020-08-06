package com.lizard.simpleweb.filter;

import com.lizard.simpleweb.token.CsrfToken;
import com.lizard.simpleweb.token.CsrfTokenRepository;
import com.lizard.simpleweb.token.HttpSessionCsrfTokenRepository;
import com.lizard.simpleweb.utils.UrlUtil;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-06 23:36
 */
public class CsrfFilter extends OncePerRequestFilter {
    private CsrfTokenRepository tokenRepository;

    public CsrfFilter(CsrfTokenRepository tokenRepository) {
        Assert.notNull(tokenRepository, "csrfTokenRepository cannot be null");
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        tokenRepository = new HttpSessionCsrfTokenRepository();

        CsrfToken csrfToken = tokenRepository.loadToken(request);
        final boolean missingToken = csrfToken == null;

        if (missingToken) {
            csrfToken = tokenRepository.generateToken();
            tokenRepository.saveToken(csrfToken, request, response);
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

            } else {

            }
        }
        filterChain.doFilter(request, response);
    }
}
