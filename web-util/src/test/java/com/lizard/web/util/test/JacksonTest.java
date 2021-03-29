package com.lizard.web.util.test;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lizard.web.util.jackson.JsonUtil;
import com.lizard.web.util.test.model.User;
import com.lizard.web.util.test.util.TestUtil;

/**
 * 描述：Jackson测试类
 *
 * @author x
 * @since 2020-07-30 22:58
 */
public class JacksonTest {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * jackson默认配置
     */
    @BeforeEach
    public void configureJackson() {
        // SerializationFeature for changing how JSON is written

        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // JsonParser.Feature for configuring parsing settings:

        // to allow C/C++ style comments in JSON (non-standard, disabled by default)
        // (note: with Jackson 2.5, there is also `mapper.enable(feature)` /
        // `mapper.disable(feature)`)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // to allow (non-standard) unquoted field names in JSON:
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // to allow use of apostrophes (single quotes), non standard
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    @Test
    public void testObjectMapper() throws JsonProcessingException {
        String userStr = mapper.writeValueAsString(TestUtil.getUser());
        System.out.println("userStr = " + userStr);
        User user = mapper.readValue(userStr, User.class);
        // 默认属性要提供无参构造方法
        System.out.println("user = " + user);

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "zack");
        map.put(2, "alex");
        map.put(3, "lisa");

        String mapStr = mapper.writeValueAsString(map);
        System.out.println("mapStr = " + mapStr);
        Map<Integer, String> readMap = mapper.readValue(mapStr, new TypeReference<Map<Integer, String>>() {});
        System.out.println("readMap = " + readMap);
    }

    @Test
    public void testCustomDeserializer() {}

    @Test
    public void testTreeModel() throws JsonProcessingException {
        User user = TestUtil.getUser();
        String userStr = mapper.writeValueAsString(user);
        System.out.println("userStr = " + userStr);

        JsonNode root = mapper.readTree(userStr);

        JsonNode wife = root.get("wife");
        boolean isPojo = wife.isPojo();
        System.out.println("wife = " + isPojo);

        String wifeStr = wife.toString();
        System.out.println("wifeStr = " + wifeStr);

        boolean isObject = wife.isObject();
        System.out.println("isObject = " + isObject); // isPoJo false

        JsonNodeType nodeType = wife.getNodeType();
        System.out.println("nodeType = " + nodeType); // Object

        JsonNode wifeNode = root.with("wife");
        String username = wifeNode.findValue("username").asText();
        System.out.println("username = " + username);

        /*
         * JsonNode.with()方法：
         * Method that can be called on Object nodes, to access a property
         * that has Object value; or if no such property exists, to create,
         * add and return such Object node.
         * If the node method is called on is not Object node,
         * or if property exists and has value that is not Object node,
         * {@link UnsupportedOperationException} is thrown
         */
        ObjectNode score = wife.with("score");
        score.put("math", 56.8);
        System.out.println("score.asText() = " + score.asText());
        System.out.println("wife.toString() = " + wife.toPrettyString());

        // java对象转JsonNode
        JsonNode jsonNode = mapper.valueToTree(user);
        System.out.println("jsonNode.toPrettyString() = " + jsonNode.toPrettyString());

        // JsonNode转Java对象
        User treeUser = mapper.treeToValue(jsonNode, User.class);
        System.out.println("treeUser = " + treeUser.equals(user));

        // 定义日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);
        userStr = mapper.writeValueAsString(user);
        System.out.println("userStr = " + userStr);

        // 路径查找
        JsonNode atNode = jsonNode.at("/wife/username");
        String atNodeUserName = atNode.asText("rose");
        int id = atNode.asInt(2);
        System.out.println("atNodeUserName = " + atNodeUserName);
        System.out.println("id = " + id);

        // 创建ObjectNode
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set("username", objectNode.textNode("jack"));
        objectNode.set("score", objectNode.numberNode(32.5));
        System.out.println("objectNode.toPrettyString() = " + objectNode.toPrettyString());
    }

    /**
     * json解析器
     *
     * @throws IOException
     *             IO异常
     */
    @Test
    public void testJsonParser() throws IOException {
        String content = mapper.writeValueAsString(TestUtil.getUser());
        System.out.println("content = " + content);
        // 创建JsonParser
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(content);
        // 解析json中所有密码字段
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            if (jsonToken == JsonToken.FIELD_NAME) {
                String currentName = parser.getCurrentName();
                jsonToken = parser.nextToken();
                if (jsonToken == JsonToken.VALUE_STRING && "password".equals(currentName)) {
                    String value = parser.getValueAsString();
                    System.out.println(currentName + ":" + value);
                }
            }
        }
    }

    /** JsonGenerator用于从Java对象（或代码从中生成JSON的任何数据结构）生成JSON */
    @Test
    public void testJsonGenerator() throws IOException {
        // 创建JsonGenerator
        JsonFactory factory = mapper.getFactory();
        try (StringWriter writer = new StringWriter()) {
            JsonGenerator jsonGenerator = factory.createGenerator(writer);

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("username", "blob");
            jsonGenerator.writeNumberField("score", 36.5);
            jsonGenerator.writeEndObject();

            jsonGenerator.flush();
            jsonGenerator.close();

            writer.flush();
            String json = writer.toString();
            System.out.println("json = " + json);
        }
    }

    @Test
    public void testJsonUtil() {
        User user = TestUtil.getUser();
        String jsonStr = JsonUtil.toJsonString(user);
        System.out.println("jsonStr = " + jsonStr);

        List<String> list = TestUtil.getStringList();
        String[] arr = {"alex", "bob", "clerk"};

        String listString = JsonUtil.toJsonString(arr);
        System.out.println("listString = " + listString);
        List<String> list1 = JsonUtil.parseArray(listString, String.class);
        System.out.println("list1 = " + list1);

        Map<String, Object> map = new HashMap<>();
        map.put("userName", "alex");
        map.put("birth", new Date());
        String mapString = JsonUtil.toJsonString(map);
        System.out.println("mapString = " + mapString);

        Map<String, String> parseMap = JsonUtil.parseMap(mapString, String.class);

        System.out.println("parseMap = " + parseMap);
    }

    @Test
    public void testJsonSanitizer() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "John\",\\\"role\":\"admin");
        map.put("password", "&t34@yu%5+");
        map.put("feature", "<script>alert(1)'</script>");
        map.put("test", 0xAB);
        map.put("test1", 012);
        map.put("test2", 'a');
        map.put("test3", "[1,2,3,]");
        String mapString = JsonUtil.toJsonString(map);
        System.out.println("mapString = " + mapString);

        Map<String, String> parseMap = JsonUtil.parseMap(mapString, String.class);

        System.out.println("parseMap = " + parseMap);
    }

    @Test
    public void testJsonDeserializeAnnotation() throws IOException {
        Map<String, Object> apiMap = new HashMap<>();

        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> subMap = new HashMap<>();
        subMap.put("enable", "true");
        subMap.put("timeOut", "10");
        map.put("http", subMap);
        Map<String, String> subMap1 = new HashMap<>();
        subMap1.put("host", "127.0.0.1");
        subMap1.put("port", "8089");
        map.put("cros", subMap1);

        apiMap.put("api_name", "com.lizard.it.odm");
        apiMap.put("api_setting", map);

        String json = mapper.writeValueAsString(apiMap);
        System.out.println("json = " + json);

        Api api = mapper.readValue(json, Api.class);

        System.out.println("api = " + mapper.writeValueAsString(api));
    }

}

class Api {
    @JsonProperty("api_name")
    private String apiName;

    @JsonProperty("api_setting")
    @JsonDeserialize(using = PluginDeserializer.class)
    private Set<Plugin> plugins;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Set<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(Set<Plugin> plugins) {
        this.plugins = plugins;
    }
}

class Plugin {
    private String pluginName;

    private Set<PluginParam> params = new HashSet<>();

    public static class PluginParam {
        private String paramName;

        private String paramValue;

        public PluginParam(String paramName, String paramValue) {
            this.paramName = paramName;
            this.paramValue = paramValue;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public Set<PluginParam> getParams() {
        return params;
    }

    public void setParams(Set<PluginParam> params) {
        this.params = params;
    }

    public void add(String paramName, String paramValue) {
        params.add(new PluginParam(paramName, paramValue));
    }
}

class PluginDeserializer extends JsonDeserializer<Set<Plugin>> {

    @Override
    public Set<Plugin> deserialize(JsonParser parser, DeserializationContext context)
        throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.readValueAsTree();
        Set<Plugin> plugins = new HashSet<>();

        treeNode.fieldNames().forEachRemaining((field) -> {
            Plugin plugin = new Plugin();
            plugin.setPluginName(field);

            TreeNode paramNode = treeNode.get(field);

            paramNode.fieldNames().forEachRemaining((key) -> {
                TreeNode valueNode = paramNode.get(key);
                plugin.add(key, valueNode.toString());
            });

            plugins.add(plugin);
        });

        return plugins;
    }
}
