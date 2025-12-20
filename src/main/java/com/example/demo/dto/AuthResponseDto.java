package com.example.demo.dto;

public class AuthResponseDto {

    private String token;
    private String email;
    private String role;

    public AuthResponseDto() {
    }

    public AuthResponseDto(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    // ===== GETTERS =====

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // ===== SETTERS =====

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
