package com.example.product_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final byte[] secretKeyBytes;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Ubah string secret ke byte array untuk kunci HMAC
        this.secretKeyBytes = secret.getBytes();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
