package com.player32611.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;


public class JwtUtil {


    /**
     * 生成 JWT
     *
     * @param secretKey 秘钥
     * @param ttlMillis 过期时间
     * @param claims    自定义信息
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ttlMillis);

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }



    /**
     * 解析 JWT
     *
     * @param secretKey 秘钥
     * @param token JWT字符串
     */
    public static Claims parseJWT(String secretKey, String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}