package com.lizard.simpleweb.util;

/**
 * 描述：Int工具类
 *
 * @author x
 * @since 2020-07-06 22:08
 */
public class Int32 {
    /**
     * 字符串解析为int
     *
     * @param value        被解析字符串
     * @param defaultValue 默认值
     * @return 解析结果
     */
    public static int parse(String value, int defaultValue) {
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            int index = value.indexOf('.');
            if (index != -1) {
                return parse(value.substring(0, index), defaultValue);
            }
        }
        return defaultValue;
    }
}
