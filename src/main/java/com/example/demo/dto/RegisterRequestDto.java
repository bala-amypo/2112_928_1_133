package com.example.demo.dto;

public class RegisterRequestDto {

    private String email;
    private String username;
    private String fullName;
    private String password;

    public RegisterRequestDto() {}

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username != null ? username : email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
