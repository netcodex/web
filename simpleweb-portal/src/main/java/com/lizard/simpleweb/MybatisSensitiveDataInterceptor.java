package com.lizard.simpleweb;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-05 23:19
 */
@Component
public class MybatisSensitiveDataInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        final Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)args[0];
        Object parameters = args[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameters);
        Configuration configuration = mappedStatement.getConfiguration();
        Log stmtLog = mappedStatement.getStatementLog();
        stmtLog.debug(getformattedParamterString(configuration, boundSql));
        return invocation.proceed();
    }

    private String getformattedParamterString(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (!CollectionUtils.isEmpty(parameterMappings) && Objects.nonNull(parameterObject)) {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                }
            }
        }
        return "sql";
    }
}
