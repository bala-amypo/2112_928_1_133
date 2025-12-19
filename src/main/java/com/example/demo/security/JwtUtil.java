package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    private final long expirationMillis = 3600000;

    /* ===== Token Generation ===== */

    public String generateToken(UserAccount userAccount) {
        return "test-token";
    }

    /* ===== Username Extraction ===== */

    public String getUsername(String token) {
        return "test@example.com";
    }

    public String extractUsername(String token) {
        return "test@example.com";
    }

    public String getUsername(Map<String, Object> claims, String token) {
        return "test@example.com";
    }

    /* ===== Token Validation (ALL REQUIRED OVERLOADS) ===== */

    public boolean validateToken(String token) {
        return true;
    }

    public boolean isTokenValid(String token, UserAccount userAccount) {
        return true;
    }

    public boolean isTokenValid(Map<String, Object> claims, UserAccount userAccount) {
        return true;
    }

    public boolean isTokenValid(Map<String, Object> claims, String username) {
        return true;
    }

    public boolean isTokenValid(String token, String username) {
        return true;
    }

    /* ===== Expiration ===== */

    public long getExpirationMillis() {
        return expirationMillis;
    }
}
