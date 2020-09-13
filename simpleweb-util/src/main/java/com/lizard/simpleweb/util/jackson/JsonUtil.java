package com.lizard.simpleweb.util.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.lizard.simpleweb.util.jackson.databind.DateTimeDeserializer;
import com.lizard.simpleweb.util.jackson.databind.DateTimeSerializer;
import com.lizard.simpleweb.util.jackson.exception.JsonException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    public static final ObjectMapper defaultMapper = defaultBuild().build();

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

    public static boolean isJson(String var) {
        try {
            defaultMapper.readTree(var);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public static <T> String toJsonString(T var) {
        try {
            return defaultMapper.writeValueAsString(var);
        } catch (JsonProcessingException e) {
            throw new JsonException(e.getMessage() + ", data: " + var);
        }
    }

    public static <T> T parse(String jsonStr, Class<T> toValueType) {
        return parse(jsonStr, (Type) toValueType);
    }

    public static <T> T parse(String jsonStr, TypeReference<T> toValueType) {
        return parse(jsonStr, toValueType.getType());
    }

    private static <T> T parse(String jsonStr, Type type) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        JavaType javaType = defaultMapper.getTypeFactory().constructType(type);
        try {
            return defaultMapper.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            throw new JsonException(
                    e.getMessage() + ", json: {}, type: {}", jsonStr, type.getTypeName());
        }
    }

    public static <V> Map<String, V> parseMap(String jsonStr, Class<V> valueType) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        MapType mapType =
                defaultMapper
                        .getTypeFactory()
                        .constructMapType(HashMap.class, String.class, valueType);
        try {
            return defaultMapper.readValue(jsonStr, mapType);
        } catch (JsonProcessingException e) {
            throw new JsonException(
                    e.getMessage() + ", json: {}, expected value type: {}", jsonStr, valueType);
        }
    }

    public static <T> List<T> parseArray(String jsonStr, Class<T> elementType) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        CollectionType collectionType =
                defaultMapper
                        .getTypeFactory()
                        .constructCollectionType(ArrayList.class, String.class);
        try {
            return defaultMapper.readValue(jsonStr, collectionType);
        } catch (JsonProcessingException e) {
            throw new JsonException(
                    e.getMessage() + ", json: {}, expected element type: {}", jsonStr, elementType);
        }
    }
}
