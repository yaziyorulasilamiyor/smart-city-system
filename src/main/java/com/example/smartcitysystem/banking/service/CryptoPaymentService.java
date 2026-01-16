package com.example.smartcitysystem.banking.service;

import com.example.smartcitysystem.banking.Transaction;
import com.example.smartcitysystem.banking.TransactionRepository;
import com.example.smartcitysystem.banking.crypto.CryptoAdapterFactory;
import com.example.smartcitysystem.banking.crypto.CryptoResult;
import com.example.smartcitysystem.banking.dto.CryptoPayRequest;
import org.springframework.stereotype.Service;
import java.time.Instant;


@Service
public class CryptoPaymentService {

    private final TransactionRepository txRepo;

    public CryptoPaymentService(TransactionRepository txRepo) {
        this.txRepo = txRepo;
    }

    public CryptoResult payCrypto(String username, CryptoPayRequest req) {
        var adapter = CryptoAdapterFactory.getInstance().of(req.getCurrency());
        var result = adapter.pay(username, req.getAmount(), req.getService());

        Transaction tx = new Transaction();
        tx.setUsername(username);
        tx.setAmount(req.getAmount());
        tx.setCurrency(result.getCurrency());
        tx.setService(req.getService());
        tx.setStatus(result.getStatus());
        // tx.setType("CRYPTO");
        tx.setTs(Instant.now());

        txRepo.save(tx);
        return result;
    }
}
