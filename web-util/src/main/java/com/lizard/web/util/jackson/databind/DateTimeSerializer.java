package com.lizard.web.util.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.lizard.web.util.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * 描述：自定义时间序列化
 *
 * @author x
 * @since 2020-09-12 0:57
 */
public class DateTimeSerializer extends StdSerializer<Date> {
    public DateTimeSerializer() {
        super(Date.class);
    }

    @Override
    public void serialize(Date value, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
        generator.writeString(value != null ? DateUtil.toStringDateFormat(value) : null);
    }
}
