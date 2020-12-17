package com.lizard.web.util.test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.lizard.web.util.jackson.databind.JsonSanitizer;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author x
 * @since 2020-09-23 22:01
 */
public class JsonInjectionTest {
    @Test
    public void testIpValidator() {
        boolean ipValid = InetAddressValidator.getInstance().isValid("127.0.0.1");
        System.out.println("ipValid = " + ipValid);
        boolean domainValid = DomainValidator.getInstance(true).isValid("localhost.huawei.com");
        System.out.println("domainValid = " + domainValid);
    }

    @Test
    public void testSanitizer() {
        JsonSanitizer.sanitize("");
    }
}

class UserManager {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        try {
            userManager.addUser("John\",\"role\":\"admin", "pass123", "default");

            Map<String, String> userInfo = userManager.getUserInfo();
            System.out.println(userInfo.get("role"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String password, String role) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();

        JsonGenerator jGenerator =
                jsonFactory.createGenerator(
                        new File("C:\\Users\\x\\Desktop\\user_info.json"), JsonEncoding.UTF8);

        jGenerator.writeStartObject();

        jGenerator.writeFieldName("username");
        jGenerator.writeString("\"" + username + "\"");

        jGenerator.writeFieldName("password");
        jGenerator.writeRawValue("\"" + password + "\"");

        jGenerator.writeFieldName("role");
        jGenerator.writeRawValue("\"default\"");

        jGenerator.writeEndObject();

        jGenerator.close();
    }

    public Map<String, String> getUserInfo() throws JsonParseException, IOException {

        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jParser =
                jsonFactory.createParser(new File("C:\\Users\\x\\Desktop\\user_info.json"));

        Map<String, String> userInfo = new HashMap<>();

        while (jParser.nextToken() != JsonToken.END_OBJECT) {

            String fieldName = jParser.getCurrentName();

            if ("username".equals(fieldName)) {
                jParser.nextToken();
                userInfo.put(fieldName, jParser.getText());
            }

            if ("password".equals(fieldName)) {
                jParser.nextToken();
                userInfo.put(fieldName, jParser.getText());
            }

            if ("role".equals(fieldName)) {
                jParser.nextToken();
                userInfo.put(fieldName, jParser.getText());
            }

            if (userInfo.size() == 3) break;
        }

        jParser.close();

        return userInfo;
    }
}
