package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String register(String u, String p) { return "OK"; }
    public String login(String u, String p) { return "TOKEN"; }
}
