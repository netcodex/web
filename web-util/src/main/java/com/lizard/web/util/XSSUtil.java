package com.lizard.web.util;

import org.apache.commons.text.StringEscapeUtils;

/**
 * 描述：Xss注入处理工具类
 *
 * @author x
 * @since 2020-07-07 22:58
 */
public class XSSUtil {

    public static String escapeScript(String script) {
        return StringEscapeUtils.escapeEcmaScript(script);
    }
}
