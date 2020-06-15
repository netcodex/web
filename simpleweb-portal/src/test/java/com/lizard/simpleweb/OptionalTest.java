package com.lizard.simpleweb;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * 描述：测试Optional用法
 *
 * @author x
 * @since 2020-06-14 23:40
 */
public class OptionalTest {
    @Test
    public void testOptional() {
        String text = "apic";
        String noable = null;
        // 1. 创建Optional
        Optional<String> op1 = Optional.of(text);
        Optional<Object> op2 = Optional.empty();
        Optional<String> op3 = Optional.ofNullable(noable);
        // Optional<String> op4 = Optional.of(noable); // 参数必须不为空，不然编译时即直接抛出空指针异常：Objects.requireNonNull
        Optional<String> op5 = Optional.ofNullable(text);
        // 2. 判断是否有值
        boolean b1 = op1.isPresent();
        boolean b2 = op2.isPresent();
        boolean b3 = op3.isPresent();
        boolean b5 = op5.isPresent();
        System.out.println("b1 = " + b1);// true
        System.out.println("b2 = " + b2);// false
        System.out.println("b3 = " + b3);// false
        System.out.println("b5 = " + b5);// true
        op1.ifPresent(s -> System.out.println("s = " + s));// apic
        // 3. 取值
        String v1 = op1.get();
        // Object v2 = op2.get(); //抛异常：NoSuchElementException: No value present
        // String v3 = op3.get(); //抛异常：NoSuchElementException: No value present
        String v5 = op5.get();
        System.out.println("v1 = " + v1);// apic
        // System.out.println("v2 = " + v2);
        // System.out.println("v3 = " + v3);
        System.out.println("v5 = " + v5);// apic
        // 4. 返回值（如果存在）；反之，返回其他。
        String s1 = op1.orElse("default");
        Object s2 = op2.orElse("default");
        String s3 = op3.orElse("default");
        String s4 = op5.orElse("default");
        System.out.println("s1 = " + s1);// apic
        System.out.println("s2 = " + s2);// default
        System.out.println("s3 = " + s3);// default
        System.out.println("s4 = " + s4);// apic

    }
}
