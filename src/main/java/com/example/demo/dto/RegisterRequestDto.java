package com.example.demo.dto;

public class RegisterRequestDto {

    private String email;
    private String fullName;
    private String password;
    private String role;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String email, String fullName, String password, String role) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    // ===== GETTERS =====

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // ===== SETTERS =====

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
