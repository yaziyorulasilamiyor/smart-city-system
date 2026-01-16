package com.example.smartcitysystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SeedConfig {

    private final UserRepository userRepository;

    public SeedConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    org.springframework.boot.CommandLineRunner seedUsers(PasswordEncoder encoder) {
        return args -> {
            seed("admin", "admin123", Role.ADMIN, encoder);
            seed("resident", "res123", Role.RESIDENT, encoder);
            seed("controller", "ctrl123", Role.CITY_CONTROLLER, encoder);
        };
    }

    private void seed(String u, String p, Role r, PasswordEncoder enc) {
        if (userRepository.findByUsername(u).isPresent()) return;
        AppUser user = new AppUser();
        user.setUsername(u);
        user.setPasswordHash(enc.encode(p));
        user.setRole(r);
        userRepository.save(user);
    }
}
