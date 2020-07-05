package com.lizard.simpleweb.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-02 21:33
 */
public class SensitiveUtil {

    /**
     * 过滤字段
     * 
     * @param t
     *            普通JO
     * @param excludeFieldNames
     *            过滤字段
     * @return 过滤字段后的字符串
     */
    public static <T> String sensitiveFieldFilter(T t, String[] excludeFieldNames) {
        return ReflectionToStringBuilder.toStringExclude(t, excludeFieldNames);
    }

    public static String maskFieldString() {
        char singleQuote = 39;
        char asterisk = 42;
        return StringUtils.wrap(StringUtils.repeat('*', 10), '\'');
    }
}
