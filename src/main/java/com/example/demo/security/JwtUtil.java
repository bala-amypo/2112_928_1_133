// package com.example.demo.security;

// import com.example.demo.entity.UserAccount;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// @Component
// public class JwtUtil {
    
//     @Value("${jwt.secret:mySecretKey}")
//     private String secret;
    
//     @Value("${jwt.expiration:86400000}")
//     private Long expiration;
    
//     private Key getSigningKey() {
//         return Keys.hmacShaKeyFor(secret.getBytes());
//     }
    
//     public String generateToken(Map<String, Object> claims, String subject) {
//         return createToken(claims, subject);
//     }
    
//     public String generateToken(UserAccount user) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("role", user.getRole());
//         return createToken(claims, user.getEmail());
//     }
    
//     private String createToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }
    
//     public Boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
//             return !isTokenExpired(token);
//         } catch (Exception e) {
//             return false;
//         }
//     }
    
//     public String getUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }
    
//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }
    
//     public Date extractExpiration(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }
    
//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }
    
//     private Claims extractAllClaims(String token) {
//         return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
//     }
    
//     private Boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }
    
//     public boolean isTokenValid(String token, String username) {
//         try {
//             String tokenUsername = extractUsername(token);
//             return tokenUsername.equals(username) && !isTokenExpired(token);
//         } catch (Exception e) {
//             return false;
//         }
//     }
    
//     public long getExpirationMillis() {
//         return expiration;
//     }
// }
package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long expirationMillis = 1000 * 60 * 60 * 24;

    // REQUIRED: Map + username version
    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    // REQUIRED: UserAccount version
    public String generateToken(UserAccount user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    // REQUIRED by tests
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // REQUIRED by tests
    public boolean isTokenValid(String token, String username) {
        return username.equals(getUsername(token)) && !isTokenExpired(token);
    }

    public long getExpirationMillis() {
        return expirationMillis;
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
