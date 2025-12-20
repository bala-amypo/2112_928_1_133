package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // 🔐 Secret key (HS256 requires >= 256 bits)
    private static final Key SECRET_KEY =
            Keys.hmacShaKeyFor(
                    "mysecretkeymysecretkeymysecretkey12345".getBytes()
            );

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // =====================================================
    // 🔥 ORIGINAL METHOD (DO NOT REMOVE)
    // =====================================================
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // =====================================================
    // 🔥 TEST REQUIRED OVERLOAD (Map + String)
    // =====================================================
    public String generateToken(
            Map<String, Object> claims,
            String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // =====================================================
    // 🔥 TEST REQUIRED METHOD
    // =====================================================
    public long getExpirationMillis() {
        return EXPIRATION_TIME;
    }

    // =====================================================
    // OPTIONAL (used by security filters)
    // =====================================================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public String getUsername(String token) {
        return extractUsername(token);
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username)
                && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
