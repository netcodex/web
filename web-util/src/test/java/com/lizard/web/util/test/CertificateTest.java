package com.lizard.web.util.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import org.junit.jupiter.api.Test;

public class CertificateTest {
    @Test
    public void testCertificateParse() throws CertificateException, FileNotFoundException {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        X509Certificate certificate =
            (X509Certificate)fact.generateCertificate(new FileInputStream("C:\\Users\\X\\Desktop\\baidu.cer"));
        System.out.println("certificate = " + certificate);
    }
}
