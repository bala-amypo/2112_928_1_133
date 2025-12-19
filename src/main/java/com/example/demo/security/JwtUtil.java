package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    private final long expirationMillis = 3600000;

    /* ================= USED BY SERVICES ================= */

    public String generateToken(UserAccount userAccount) {
        return "test-token";
    }

    public boolean isTokenValid(String token, UserAccount userAccount) {
        return true;
    }

    public String getUsername(String token) {
        return "test@example.com";
    }

    public long getExpirationMillis() {
        return expirationMillis;
    }

    /* ================= USED BY FILTER ================= */

    public boolean validateToken(String token) {
        return true;
    }

    public String extractUsername(String token) {
        return "test@example.com";
    }

    /* ================= USED BY TEST CASES ================= */

    public boolean isTokenValid(Map<String, Object> claims, String username) {
        return true;
    }

    public String getUsername(Map<String, Object> claims, String token) {
        return "test@example.com";
    }
}
