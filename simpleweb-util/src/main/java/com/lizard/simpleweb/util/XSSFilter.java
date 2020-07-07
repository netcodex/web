package com.lizard.simpleweb.util;

import org.apache.commons.text.StringEscapeUtils;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-07 22:58
 */
public class XSSFilter {

    public static String escapeScript(String script) {
        return StringEscapeUtils.escapeEcmaScript(script);
    }
}
