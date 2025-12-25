package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET = "secret-key-demo";
    private final long EXPIRATION = 1000 * 60 * 60;

    public String generateToken(UserAccount user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // ✅ REQUIRED BY TESTS
    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody().getSubject();
    }

    // ✅ REQUIRED BY TESTS
    public String getUsername(String token) {
        return extractUsername(token);
    }

    public boolean validateToken(String token) {
        return !Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody()
                .getExpiration().before(new Date());
    }

    // ✅ REQUIRED BY TESTS
    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && validateToken(token);
    }

    public Date getExpirationDate(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody().getExpiration();
    }

    // ✅ REQUIRED BY TESTS
    public long getExpirationMillis() {
        return EXPIRATION;
    }
}
