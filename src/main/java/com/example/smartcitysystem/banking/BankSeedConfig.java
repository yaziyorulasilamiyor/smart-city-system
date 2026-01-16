package com.example.smartcitysystem.banking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class

BankSeedConfig {

    @Bean
    CommandLineRunner seedAccounts(AccountRepository repo) {
        return args -> {
            repo.findByUsername("resident").orElseGet(() -> {
                Account a = new Account();
                a.setUsername("resident");
                a.setFiatBalance(1000.0);
                return repo.save(a);
            });
        };
    }
}
