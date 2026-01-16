package com.example.smartcitysystem.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.ttlMinutes:120}")
    private long ttlMinutes;

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String username, String role) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ttlMinutes, ChronoUnit.MINUTES)))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token);
    }
}
