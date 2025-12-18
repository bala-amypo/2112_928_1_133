package com.example.demo.security;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
