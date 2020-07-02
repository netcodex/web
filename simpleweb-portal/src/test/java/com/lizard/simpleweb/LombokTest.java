package com.lizard.simpleweb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：测试lombok的常用注解
 *
 * @author x
 * @since 2020-06-14 15:00
 */
public class LombokTest {
    @Test
    public void testLombok() throws FileNotFoundException {
        // 自动关闭流
        // @Cleanup
        // InputStream is = new FileInputStream("");
        LombokEntity.LombokEntityBuilder builder = new LombokEntity.LombokEntityBuilder();
        LombokEntity entity = builder.field("text").name("id").build();
    }

    @Data // 自动生成setter/getter、equals、canEqual、hashCode、toString
    @NoArgsConstructor
    // @RequiredArgsConstructor // 所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法
    @AllArgsConstructor
    @Builder // 采用Builder的模式构造实例
    @Slf4j // 获取Slf4j实例啦，直接log调用即可
    private static class LombokEntity {

        @NonNull
        private String field;

        private String name;

        private String pred;

        public void testGetLog() {
            log.info("get slf4jlog");
        }
    }
}
