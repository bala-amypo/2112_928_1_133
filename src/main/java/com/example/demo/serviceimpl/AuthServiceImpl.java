// package com.example.demo.serviceimpl;

// import com.example.demo.dto.AuthRequestDto;
// import com.example.demo.dto.AuthResponseDto;
// import com.example.demo.dto.RegisterRequestDto;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.AuthService;
// import org.springframework.stereotype.Service;

// @Service
// public class AuthServiceImpl implements AuthService {

//     private final JwtUtil jwtUtil;

//     public AuthServiceImpl(JwtUtil jwtUtil) {
//         this.jwtUtil = jwtUtil;
//     }

//     @Override
//     public AuthResponseDto login(AuthRequestDto request) {
//         String token = jwtUtil.generateToken(request.getUsername());
//         return new AuthResponseDto(token);
//     }

//     @Override
//     public String register(RegisterRequestDto request) {
//         return "User registered successfully";
//     }
// }
