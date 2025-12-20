package com.example.demo.dto;

public class AuthRequestDto {

    private String email;
    private String username;
    private String password;

    public AuthRequestDto() {}

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username != null ? username : email;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
