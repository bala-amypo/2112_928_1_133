package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthResponseDto {

    private String token;
    private LocalDateTime expiresAt;

    public AuthResponseDto() {}

    public AuthResponseDto(String token, LocalDateTime expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }
}
