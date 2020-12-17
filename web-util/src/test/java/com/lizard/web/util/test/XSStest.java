package com.lizard.web.util.test;

import com.lizard.web.util.XSSUtil;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-07 23:05
 */
public class XSStest {
    @Test
    public void testEscape() {
        String script = "JSON.parse\"<iframe>\"";
        String script2 = "JSON.parse\"<iframe>\"";
        String script3 = "<script>alter(1);</script>";
        String after = XSSUtil.escapeScript(script);
        String html4 = StringEscapeUtils.escapeHtml4(script);
        System.out.println("html4 = " + html4);
        System.out.println("after = " + after);
        String before = StringEscapeUtils.unescapeHtml4(html4);
        System.out.println("before = " + before);
    }
}
