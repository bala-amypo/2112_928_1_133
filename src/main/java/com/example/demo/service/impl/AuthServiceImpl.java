package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UserAccountRepository repo,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authManager,
            JwtUtil jwtUtil) {

        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequestDto dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        UserAccount user = new UserAccount();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());

        // ✅ ENCODE PASSWORD (THIS FIXES LOGIN)
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole("ROLE_" + dto.getRole());
        repo.save(user);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {

        // ✅ AUTHENTICATE
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
            )
        );

        UserAccount user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        String token = jwtUtil.generateToken(user);

        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setExpiresAt(
            LocalDateTime.now().plusMillis(jwtUtil.getExpirationMillis())
        );

        return response;
    }
}
