package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "secret-key-123";
    private static final long EXPIRATION = 1000 * 60 * 60;

    public String generateToken(UserAccount user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // ✅ email as username
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
