package com.lizard.simpleweb;

import com.lizard.simpleweb.utils.ConditionMap;
import org.junit.jupiter.api.Test;

/**
 * 描述：
 *
 * @author x
 * @since 2020-08-07 23:18
 */
public class ConditionMapTest {
    @Test
    public void testCreateCondition() {
        ConditionMap<String, Object> condition =
            new ConditionMap<String, Object>("id", 1001).add("name", "jack").add("email", "jack@email.com");
        System.out.println("condition = " + condition);
    }
}
