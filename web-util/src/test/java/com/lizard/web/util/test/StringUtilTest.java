package com.lizard.web.util.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * 描述：字符串工具测试类
 *
 * @author x
 * @since 2020-08-04 22:36
 */
public class StringUtilTest {
    @Test
    public void testContainsOnly() {
        String str = "*********";
        boolean contain = StringUtils.containsOnly(str, '*');
        System.out.println("contain = " + contain);
    }

    @Test
    public void testUnicode() {
        char unicode = '\u0041';
        System.out.println("unicode = " + unicode);
        int hex = 0x41;
        System.out.println("hex = " + hex);
        char hexChar = (char)hex;
        System.out.println("hexChar = " + hexChar);
    }
}
