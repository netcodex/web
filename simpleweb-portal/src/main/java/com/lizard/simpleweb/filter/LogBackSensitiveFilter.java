package com.lizard.simpleweb.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-03 0:57
 */
public class LogBackSensitiveFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getFormattedMessage().contains("Parameters")) {
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }
}
