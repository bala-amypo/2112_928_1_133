package com.example.demo.dto;

public class RegisterRequestDto {

    private String email;
    private String password;
    private String role;
    private String fullName;

    public RegisterRequestDto() {}

    public String getEmail() {
        return email;
    }

    public RegisterRequestDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public RegisterRequestDto setRole(String role) {
        this.role = role;
        return this;
    }

   
    public String getFullName() {
        return fullName;
    }

    public RegisterRequestDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
