package com.lizard.simpleweb;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.filter.EvaluatorFilter;
import ch.qos.logback.core.status.Status;

/**
 * 描述：
 *
 * @author x
 * @since 2020-07-03 1:08
 */
public class LogbackEvaluatorFilter extends EvaluatorFilter<ILoggingEvent> {

}

class SensitiveEventEvaluator implements EventEvaluator<ILoggingEvent> {

    @Override
    public boolean evaluate(ILoggingEvent event) throws NullPointerException, EvaluationException {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void addStatus(Status status) {

    }

    @Override
    public void addInfo(String msg) {

    }

    @Override
    public void addInfo(String msg, Throwable ex) {

    }

    @Override
    public void addWarn(String msg) {

    }

    @Override
    public void addWarn(String msg, Throwable ex) {

    }

    @Override
    public void addError(String msg) {

    }

    @Override
    public void addError(String msg, Throwable ex) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
