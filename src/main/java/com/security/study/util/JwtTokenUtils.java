package com.security.study.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtils {
    public static String generateToken(String account, String key, long expiredTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("account", account);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
            .signWith(getKey(key), SignatureAlgorithm.HS256)
            .compact();

    }

    public static Key getKey(String key) {
        byte[] keyBites = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBites);
    }

    public static String getAccount(String token, String key) {
        return extractClaims(token, key).get("account", String.class);
    }

    public static boolean isExpired(String token, String key) {
        Date expiredDate = extractClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    public static Claims extractClaims(String token, String key) {
        return Jwts.parserBuilder().setSigningKey(getKey(key)).build().parseClaimsJwt(token).getBody();
    }

    public static void expiredToken(String account) {
        generateToken(account, null, 0L);
    }
}