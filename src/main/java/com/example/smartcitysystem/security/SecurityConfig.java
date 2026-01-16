package com.example.smartcitysystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // logdaki "generated password" izlerini azaltır, gereksiz basic/form yok
                .httpBasic(b -> b.disable())
                .formLogin(f -> f.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // ===== PUBLIC (demo-friendly) =====
                        .requestMatchers("/", "/index.html", "/admin.html", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/actuator/health", "/actuator/info").permitAll()

                        .requestMatchers(HttpMethod.POST, "/sensor/ingest").permitAll()

                        .requestMatchers(HttpMethod.GET, "/sensor/latest").permitAll()
                        .requestMatchers(HttpMethod.GET, "/alerts/**").permitAll()

                        // ===== PROTECTED =====
                        .requestMatchers("/bank/**").hasRole("RESIDENT")

                        .requestMatchers("/actuator/metrics/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
