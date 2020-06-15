package com.lizard.simpleweb.util;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.lizard.simpleweb.WebToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：JWT工具类
 *
 * @author x
 * @since 2020-06-14 22:22
 */
@Slf4j
public class JwtHelper {
    private static final String AUDIENCE = "apiAud";
    /**
     * 解析JWT
     * 
     * @param token
     *            请求token
     * @param secret
     *            密钥
     * @return Claims
     */
    public static Optional<Claims> parseJWT(String token, String secret) {
        try {
            byte[] secretBytes = DatatypeConverter.parseBase64Binary(secret);
            JwtParser jwtParser = Jwts.parser().setSigningKey(secretBytes);
            return Optional.ofNullable(jwtParser.parseClaimsJws(token).getBody());
        } catch (ExpiredJwtException e) {
            log.error("jwt expired.");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            e.printStackTrace();
            log.error("jwt parse error: {}", e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * 生成JWT
     *
     * @return token
     */
    public static String generateJWT(String reqId, String userName, WebToken webToken) {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecret = DatatypeConverter.parseBase64Binary(webToken.getSecret());
        Key key = new SecretKeySpec(apiKeySecret, algorithm.getJcaName());
        Date now = Date.from(Instant.now());
        JwtBuilder jwtBuilder = Jwts.builder().setId(reqId).setAudience(AUDIENCE).setSubject(userName)
            .setIssuer(webToken.getIssuer()).setIssuedAt(now).signWith(algorithm, key);
        Instant exp = Instant.now().plusMillis(webToken.getExpireTime());
        return jwtBuilder.setExpiration(Date.from(exp)).setNotBefore(now).compact();
    }

}
