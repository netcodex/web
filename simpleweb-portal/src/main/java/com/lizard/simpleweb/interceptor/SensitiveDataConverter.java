package com.lizard.simpleweb.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 描述：logback信息转换器
 *
 * @author x
 * @since 2020-07-03 0:07
 */
public class SensitiveDataConverter extends MessageConverter {

    private static final String JSON_REPLACEMENT_REGEX = "$1: ****";
    private static final String JSON_KEYS =
        "[" + StringUtils.join(new String[] {"ssn", "private", "creditCard"}, '|') + "]";
    private static final Pattern JSON_PATTERN = Pattern.compile(JSON_KEYS + ": (\\S+)");

    @Override
    public String convert(ILoggingEvent event) {
        final String formattedMessage = event.getFormattedMessage();
        if (formattedMessage.contains("Parameters:")) {
            return mask(formattedMessage);
        }
        return super.convert(event);
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
