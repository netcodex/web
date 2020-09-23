package com.lizard.simpleweb.util.test;

import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.jupiter.api.Test;

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
}
