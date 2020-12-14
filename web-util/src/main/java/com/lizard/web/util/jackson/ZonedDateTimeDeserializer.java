package com.lizard.web.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * 描述：自定义时间反序列化
 *
 * @author x
 * @since 2020-08-02 20:54
 */
public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    protected ZonedDateTimeDeserializer() {
        super(ZonedDateTime.class);
    }

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        JsonToken jsonToken = jsonParser.currentToken();
        if (jsonToken == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText().trim();
        }
        return null;
    }
}
