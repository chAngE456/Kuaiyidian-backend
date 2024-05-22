package com.lao.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtil {

    private static final String SECRET_KEY = "6jUuS8JXd5cZ0kX2xT1e7v2w6r75q6o7w1u7t7rYp5a8"; // 应使用更安全的方式存储密钥

    public String createToken(String name) {
        // 设置JWT的签发时间、过期时间等参数
        return Jwts.builder()
                .setSubject(name) // 负载中存放的用户名或其他唯一标识
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置过期时间，10小时后过期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名算法和密钥
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY) // 签名密钥，和生成的密钥保持一致
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // token过期或验证失败会抛出异常
            return null;
        }
    }

    public String getUidByToken(String token) {
        Claims claims = validateToken(token);
        return claims.getSubject();
    }
}