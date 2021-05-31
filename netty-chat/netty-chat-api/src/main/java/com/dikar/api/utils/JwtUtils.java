package com.dikar.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.utils
 * @Author: yefei
 * @CreateTime: 2021-05-31 10:12
 * @Description: jwt工具类
 */
public class JwtUtils {

    public static String createToken(Map<String, Object> claims, Long ttl) {
        Key key = generateKey();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成jwt的时间点
        Long nowMillis = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 设置payload的键值队
                .setClaims(claims)
                .signWith(signatureAlgorithm, key);
        if (Objects.nonNull(ttl) && ttl > -1) {
            long expMillis = nowMillis + ttl * 1000;
            Date exp = new Date(expMillis);
            // 设置过期时间
            jwtBuilder.setExpiration(exp);
        }
        return jwtBuilder.compact();
    }

    /**
     * 生成token
     * 没有过期时间
     *
     * @param claims
     * @return
     */
    public static String createToken(Map<String, Object> claims) {
        return createToken(claims, null);
    }

    public static Claims parse(String jwt) {
        if (Objects.isNull(jwt)) {
            return null;
        }
        try {
            return Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private static SecretKey generateKey() {
        String stringKey = "tb-im";
        byte[] encodeKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }
}
