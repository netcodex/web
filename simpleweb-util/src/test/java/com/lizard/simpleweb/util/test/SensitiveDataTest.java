package com.lizard.simpleweb.util.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizard.simpleweb.util.SensitiveUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            "[" + StringUtils.join(new String[]{"ssn", "private", "creditCard"}, '|') + "]";
    private static final Pattern JSON_PATTERN = Pattern.compile(JSON_KEYS + ": (\\S+)");

    @Test
    public void testMaskString() {
        String s = SensitiveUtil.maskFieldString();
        System.out.println("s = " + s);
        String s1 = "[" + StringUtils.join(new String[]{"ssn", "private", "creditCard"}, '|') + "]";
        System.out.println("s1 = " + s1);
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

    @Test
    public void testJsonFilter() throws JsonProcessingException {
        User user = new User();
        user.setUsername("alexander");
        user.setPassword("#an&3%f4");

        Card card1 = new Card(1001, "工商银行", "897564");
        Card card2 = new Card(1006, "建设银行", "583456");
        Card card3 = new Card(1011, "农业银行", "589642");
        Card card4 = new Card(1016, "招商银行", "258945");

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        List<Card> cards1 = new ArrayList<>();
        cards1.add(card4);

        User wife = new User();
        wife.setUsername("catalina");
        wife.setPassword("&t34@yu%5+");
        wife.setCards(cards1);

        user.setCards(cards);
        user.setWife(wife);

        Map<String, Object> map = new HashMap<>();
        map.put("reqUrl", "/api/data/page");
        map.put("method", "Post");
        map.put("reqParams", "username=liwei,password=e37$af6=");
        map.put("reqBody", user);

        ObjectMapper mapper = new ObjectMapper();
        String logDetail = mapper.writeValueAsString(map);
        System.out.println("logDetail = " + logDetail);

        Object log = mapper.readValue(logDetail, Object.class);

        // SensitiveUtil.jsonFieldFilter(log);
        System.out.println("log.toString() = " + log.toString());
    }

    @Test
    public void testRegexp() {
        String test = "abcbbabcbcgbddesddfiid";
        String reg = "(a)(b)c";
        System.out.println(test.replaceAll(reg, "$1"));
    }
}
