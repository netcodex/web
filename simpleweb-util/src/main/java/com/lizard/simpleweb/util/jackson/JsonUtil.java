package com.lizard.simpleweb.util.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lizard.simpleweb.util.jackson.databind.DateTimeDeserializer;
import com.lizard.simpleweb.util.jackson.databind.DateTimeSerializer;
import com.lizard.simpleweb.util.jackson.exception.JsonException;

import java.util.Date;
import java.util.Locale;

/**
 * 描述：基于jackson的Json工具类
 *
 * @author x
 * @since 2020-07-30 23:05
 */
public class JsonUtil {
    public static final SimpleModule DATE_MODULE =
            new SimpleModule()
                    .addSerializer(new DateTimeSerializer())
                    .addDeserializer(Date.class, new DateTimeDeserializer());

    public static final ObjectMapper DEFAULT_MAPPER = defaultBuild().build();

    public static JsonMapper.Builder defaultBuild() {
        return JsonMapper.builder()
                .defaultLocale(Locale.getDefault())
                // 配置序列化级别
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                // 允许使用java注释
                .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
                // 允许存在单引号包裹的字段
                .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
                // 允许存在没用双引号包裹的字段
                .enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES)
                // 允许只有key没有value的情况
                .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
                // 支持缩进
                .enable(SerializationFeature.INDENT_OUTPUT)
                // 对象为空时不抛异常
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 时间格式
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // 禁止重复键
                .enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
                // 属性映射失败时不报错
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 时间格式化模块
                .addModule(DATE_MODULE);
    }

    public static <T> String to(T var) {
        try {
            return DEFAULT_MAPPER.writeValueAsString(var);
        } catch (JsonProcessingException e) {
            throw new JsonException(String.format("json to string error, data: %s, %s", var, e));
        }
    }
}
