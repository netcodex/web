import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.lizard.simpleweb.util.SensitiveUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-05 22:12
 */
public class SensitiveDataTest {

    private static final String JSON_REPLACEMENT_REGEX = "$0: ****";
    private static final String JSON_KEYS =
        "[" + StringUtils.join(new String[] {"ssn", "private", "creditCard"}, '|') + "]";
    private static final Pattern JSON_PATTERN = Pattern.compile(JSON_KEYS + ": (\\S+)");

    @Test
    public void testMaskString() {
        String s = SensitiveUtil.maskFieldString();
        System.out.println("s = " + s);
        String s1 = "[" + StringUtils.join(new String[] {"ssn", "private", "creditCard"}, '|') + "]";
        System.out.println("s1 = " + s1);;
        String s2 = ": (\\S+)";
    }

    @Test
    public void testMask() {
        String str = "ssn: ahkha7f8ahag871b7a==-";
        String mask = mask(str);
        System.out.println("mask = " + mask);
    }

    private String mask(String message) {
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = JSON_PATTERN.matcher(message);

        while (matcher.find()) {
            matcher.appendReplacement(buffer, JSON_REPLACEMENT_REGEX);
        }

        matcher.appendTail(buffer);

        return buffer.toString();
    }
}
