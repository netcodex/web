package com.lizard.simpleweb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：日期工具类
 *
 * @author x
 * @since 2020-08-02 20:51
 */
public class DateUtil {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> FORMATTER = new ThreadLocal<>();

    private static final Object lock = new Object();

    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = FORMATTER.get();
        if (dateFormat == null) {
            synchronized (lock) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    FORMATTER.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    public static SimpleDateFormat defaultFormatter() {
        return getDateFormat(YYYY_MM_DD_HH_MM_SS);
    }

    public static String toStringDateFormat(Date date) {
        return defaultFormatter().format(date);
    }

    public static Date toDate(String text) throws ParseException {
        if (text == null || text.length() == 0) {
            return null;
        }
        return defaultFormatter().parse(text);
    }
}
