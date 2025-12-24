package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET =
            "test-secret-key-test-secret-key-test-secret-key";
    private static final long EXPIRATION = 3600000; // 1 hour

    public String generateToken(UserAccount user) {
        return generateToken(Map.of(), user.getEmail());
    }

    // 🔹 Used directly by tests
    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Used by filter + tests
    public boolean validateToken(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return parse(token).getSubject();
    }

    // ⭐ REQUIRED BY TESTS
    public String getUsername(String token) {
        return extractEmail(token);
    }

    // ⭐ REQUIRED BY TESTS
    public boolean isTokenValid(String token, String username) {
        return validateToken(token) && extractEmail(token).equals(username);
    }

    // 🔹 Used in AuthServiceImpl
    public long getExpiry(String token) {
        return parse(token).getExpiration().getTime();
    }

    // ⭐ REQUIRED BY TESTS
    public long getExpirationMillis() {
        return EXPIRATION;
    }

    private Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
