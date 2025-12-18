package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class JwtUtil {

    public String generateToken(String username) {
        return "TOKEN_" + username + "_" + UUID.randomUUID();
    }
}
