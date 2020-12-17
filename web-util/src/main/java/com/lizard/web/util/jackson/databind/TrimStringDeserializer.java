package com.lizard.web.util.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * 描述：自定义字符串去空格反序列化器
 *
 * @author x
 * @since 2020-09-12 0:23
 */
public class TrimStringDeserializer extends StdDeserializer<String> {

    public TrimStringDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser parser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        return null;
    }
}
