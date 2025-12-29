    // package com.example.demo.security;

    // import jakarta.servlet.FilterChain;
    // import jakarta.servlet.ServletException;
    // import jakarta.servlet.http.HttpServletRequest;
    // import jakarta.servlet.http.HttpServletResponse;
    // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    // import org.springframework.security.core.context.SecurityContextHolder;
    // import org.springframework.security.core.userdetails.UserDetails;
    // import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    // import org.springframework.stereotype.Component;
    // import org.springframework.web.filter.OncePerRequestFilter;

    // import java.io.IOException;

    // @Component
    // public class JwtAuthenticationFilter extends OncePerRequestFilter {
        
    //     private final JwtUtil jwtUtil;
    //     private final CustomUserDetailsService userDetailsService;
        
    //     public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
    //         this.jwtUtil = jwtUtil;
    //         this.userDetailsService = userDetailsService;
    //     }
        
    //     @Override
    //     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
    //                                     FilterChain filterChain) throws ServletException, IOException {
            
    //         final String authorizationHeader = request.getHeader("Authorization");
            
    //         String username = null;
    //         String jwt = null;
            
    //         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    //             jwt = authorizationHeader.substring(7);
    //             try {
    //                 username = jwtUtil.extractUsername(jwt);
    //             } catch (Exception e) {
    //                 // Invalid token
    //             }
    //         }
            
    //         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
    //             if (jwtUtil.validateToken(jwt)) {
    //                 UsernamePasswordAuthenticationToken authToken = 
    //                         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    //                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //                 SecurityContextHolder.getContext().setAuthentication(authToken);
    //             }
    //         }
            
    //         filterChain.doFilter(request, response);
    //     }
    // }
    package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            String username = jwtUtil.getUsername(token);

            if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null &&
                jwtUtil.isTokenValid(token, username)) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
