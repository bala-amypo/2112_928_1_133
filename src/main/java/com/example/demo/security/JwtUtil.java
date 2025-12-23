package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "this-is-a-very-secure-secret-key-for-jwt-signing-123456";
    private static final long EXPIRATION_MS = 60 * 60 * 1000;

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(UserAccount user) {
        return Jwts.builder()
                .setSubject(user.getEmail())      // ✅ correct for 0.11.5
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()              
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
