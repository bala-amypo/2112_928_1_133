package com.example.demo.controller;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor Injection (BEST PRACTICE)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * LOGIN API
     * URL: POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody AuthRequestDto request) {

        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * REGISTER API
     * URL: POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequestDto request) {

        String message = authService.register(request);
        return ResponseEntity.ok(message);
    }
}
