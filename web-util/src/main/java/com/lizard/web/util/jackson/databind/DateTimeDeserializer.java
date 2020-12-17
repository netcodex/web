package com.lizard.web.util.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lizard.web.util.DateUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * 描述：自定义时间反序列化
 *
 * @author x
 * @since 2020-08-02 20:54
 */
public class DateTimeDeserializer extends StdDeserializer<Date> {
    public DateTimeDeserializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        JsonToken token = parser.currentToken();
        if (token == JsonToken.VALUE_STRING) {
            String text = parser.getText().trim();
            try {
                return DateUtil.toDate(text);
            } catch (ParseException e) {
                throw context.wrongTokenException(parser, Date.class, token, "date parse error");
            }
        }
        throw context.wrongTokenException(parser, Date.class, token, "expect a string");
    }
}
