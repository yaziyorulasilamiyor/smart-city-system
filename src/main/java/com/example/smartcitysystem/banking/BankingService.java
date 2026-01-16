package com.example.smartcitysystem.banking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class BankingService {

    private final AccountRepository accountRepository;
    private final TransactionRepository txRepository;

    public BankingService(AccountRepository accountRepository, TransactionRepository txRepository) {
        this.accountRepository = accountRepository;
        this.txRepository = txRepository;
    }

    @Transactional
    public Transaction payParkingFiat(String username, double amount) {
        Account acc = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction tx = new Transaction();
        tx.setUsername(username);
        tx.setService("PARKING");
        tx.setCurrency("TRY");
        tx.setAmount(amount);
        tx.setTs(Instant.now());

        if (acc.getFiatBalance() < amount) {
            tx.setStatus("FAILED");
            return txRepository.save(tx);
        }

        acc.setFiatBalance(acc.getFiatBalance() - amount);
        accountRepository.save(acc);

        tx.setStatus("SUCCESS");
        return txRepository.save(tx);
    }
}
