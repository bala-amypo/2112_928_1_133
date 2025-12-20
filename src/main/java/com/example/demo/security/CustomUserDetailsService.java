package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return new User(
                username,
                "$2a$10$dummyhashdummyhashdummyhashdummyhash",
                Collections.emptyList()
        );
    }
}
