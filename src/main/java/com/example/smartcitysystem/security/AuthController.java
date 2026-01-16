package com.example.smartcitysystem.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String token, String role) {}

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        AppUser user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials"));

        if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }

        String token = tokenService.createToken(user.getUsername(), user.getRole().name());
        return new LoginResponse(token, user.getRole().name());
    }
}
