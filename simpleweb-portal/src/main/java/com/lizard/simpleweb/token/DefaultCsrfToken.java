package com.lizard.simpleweb.token;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-06 22:49
 */
public class DefaultCsrfToken implements CsrfToken {
    private final String headerName;
    private final String parameterName;
    private final String token;

    public DefaultCsrfToken(String headerName, String parameterName, String token) {
        this.headerName = headerName;
        this.parameterName = parameterName;
        this.token = token;
    }


    @Override
    public String getHeaderName() {
        return headerName;
    }

    @Override
    public String getParameterName() {
        return parameterName;
    }

    @Override
    public String getToken() {
        return token;
    }

}
