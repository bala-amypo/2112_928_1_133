package com.example.demo.dto;

public class AuthResponseDto {

    private String token;
    private String message;
    private String role;

    public AuthResponseDto() {
    }

  
    public AuthResponseDto(String token) {
        this.token = token;
    }

    // existing constructor (tests / swagger may expect this)
    public AuthResponseDto(String token, String message, String role) {
        this.token = token;
        this.message = message;
        this.role = role;
    }

    // ===== GETTERS =====

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    // ===== SETTERS =====

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
