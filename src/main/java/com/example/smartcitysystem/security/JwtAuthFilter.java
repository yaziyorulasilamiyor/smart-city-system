package com.example.smartcitysystem.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public JwtAuthFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);

            try {
                var jws = tokenService.parse(token);
                Claims claims = jws.getBody();

                String username = claims.getSubject();
                String role = claims.get("role", String.class);
                String authority = role.startsWith("ROLE_") ? role : "ROLE_" + role;

                var authorities = List.of(new SimpleGrantedAuthority(authority));
                var principal = new org.springframework.security.core.userdetails.User(username, "", authorities);

                var authentication = new UsernamePasswordAuthenticationToken(principal, token, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception ignored) {
                // token bozuksa auth set etmesin diye
            }
        }

        chain.doFilter(request, response);
    }
}
