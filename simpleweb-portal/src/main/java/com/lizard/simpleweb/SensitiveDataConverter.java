package com.lizard.simpleweb;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 描述：logback信息转换器
 *
 * @author x
 * @since 2020-07-03 0:07
 */
public class SensitiveDataConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        String formattedMessage = event.getFormattedMessage();
        if (formattedMessage.contains("Parameters:")) {
        }
        return super.convert(event);
    }
}
