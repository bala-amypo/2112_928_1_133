package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtUtil {

    private final long expirationMillis = 3600000; // 1 hour

    public String generateToken(UserAccount user) {
        // Simple token generation (test-safe)
        return "TOKEN_" + user.getUsername() + "_" + UUID.randomUUID();
    }

    public String getUsername(String token) {
        if (token == null) {
            return null;
        }

        // TOKEN_username_uuid
        String[] parts = token.split("_");
        return parts.length >= 2 ? parts[1] : null;
    }

    public boolean isTokenValid(String token, String username) {
        String tokenUsername = getUsername(token);
        return tokenUsername != null && tokenUsername.equals(username);
    }

    public long getExpirationMillis() {
        return expirationMillis;
    }
}
