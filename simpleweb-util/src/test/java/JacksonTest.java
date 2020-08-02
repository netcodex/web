import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        // (note: with Jackson 2.5, there is also `mapper.enable(feature)` / `mapper.disable(feature)`)
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
    public void testCustomDeserializer() {

    }

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

    @Test
    public void testJsonParser() {

    }

}
