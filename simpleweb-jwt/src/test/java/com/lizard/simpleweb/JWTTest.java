package com.lizard.simpleweb;

import java.util.Date;
import java.util.Optional;

import com.lizard.simpleweb.model.WebToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.lizard.simpleweb.util.JwtHelper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * 描述：测试JWT，@SpringBootTest 默认会查找@SpringBootAppication注解的Context
 *
 * @author x
 * @since 2020-06-14 22:48
 */
@SpringBootTest
public class JWTTest {
    @Autowired
    private WebToken webToken;

    @Test
    public void testJWTCreate() {
        Assert.notNull(webToken, "webToken can not be null");
        String token = JwtHelper.generateJWT("1001", "zack", webToken);
        System.out.println("token = " + token);
    }

    @Test
    public void testJWTParse() {
        String token =
            "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAxIiwiYXVkIjoiYXBpQXVkIiwic3ViIjoiemFjayIsImlzcyI6InJlc3RhcGl1c2VyIiwiaWF0IjoxNTkyMjM1MDIyLCJleHAiOjE1OTIyMzU2MjIsIm5iZiI6MTU5MjIzNTAyMn0._ukpg7o5ko9ZMfAb2MrFxHuGnpy1jM-44MFE9mT8XoM";
        Optional<Claims> claims = JwtHelper.parseJWT(token, webToken.getSecret());
        Claims claim = claims.orElse(new DefaultClaims());
        String issuer = claim.getIssuer();

        String id = claim.getId();
        System.out.println("id = " + id);
        System.out.println("issuer = " + issuer);
        String audience = claim.getAudience();
        System.out.println("audience = " + audience);
        String subject = claim.getSubject();
        System.out.println("subject = " + subject);
        Date expiration = claim.getExpiration();
        System.out.println("expiration = " + expiration);
    }

}
