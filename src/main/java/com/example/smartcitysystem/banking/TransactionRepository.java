package com.example.smartcitysystem.banking;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop50ByUsernameOrderByTsDesc(String username);
}
