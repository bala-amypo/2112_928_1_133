package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public String generateToken(UserAccount user) {
        return "dummy-token";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    public String extractUsername(String token) {
        return "test@example.com";
    }

    public Map<String, Object> getClaims(UserAccount user) {
        return new HashMap<>();
    }
}
