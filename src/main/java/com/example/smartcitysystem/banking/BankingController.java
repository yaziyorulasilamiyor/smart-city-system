package com.example.smartcitysystem.banking;

import com.example.smartcitysystem.banking.dto.CryptoPayRequest;
import com.example.smartcitysystem.banking.crypto.CryptoResult;
import com.example.smartcitysystem.banking.service.CryptoPaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/bank")
public class BankingController {

    private final BankingService bankingService;
    private final AccountRepository accountRepository;
    private final TransactionRepository txRepository;
    private final CryptoPaymentService cryptoPaymentService;

    public BankingController(
            BankingService bankingService,
            AccountRepository accountRepository,
            TransactionRepository txRepository,
            CryptoPaymentService cryptoPaymentService
    ) {
        this.bankingService = bankingService;
        this.accountRepository = accountRepository;
        this.txRepository = txRepository;
        this.cryptoPaymentService = cryptoPaymentService;
    }

    public record PayRequest(double amount) {}

    @GetMapping("/me")
    @PreAuthorize("hasRole('RESIDENT')")
    public Account myAccount(Authentication auth) {
        String username = auth.getName();
        return accountRepository.findByUsername(username).orElseThrow();
    }

    @PostMapping("/parking/pay")
    @PreAuthorize("hasRole('RESIDENT')")
    public Transaction payParking(@RequestBody PayRequest req, Authentication auth) {
        String username = auth.getName();
        var cmd = new com.example.smartcitysystem.banking.command.PayParkingCommand(
                bankingService, username, req.amount()
        );
        return cmd.execute();
    }

    @GetMapping("/my-tx")
    @PreAuthorize("hasRole('RESIDENT')")
    public List<Transaction> myTx(Authentication auth) {
        String username = auth.getName();
        return txRepository.findTop50ByUsernameOrderByTsDesc(username);
    }

    @PostMapping("/crypto/pay")
    @PreAuthorize("hasRole('RESIDENT')")
    public Object payCrypto(@RequestBody CryptoPayRequest req, Authentication auth) {
        String username = auth.getName();
        var cmd = new com.example.smartcitysystem.banking.command.PayCryptoCommand(
                cryptoPaymentService, username, req
        );
        CryptoResult result = cmd.execute();

        return ResponseEntity.ok(java.util.Map.of(
                "amount", req.getAmount(),
                "currency", result.getCurrency(),
                "service", req.getService(),
                "txHash", result.getTxHash(),
                "status", result.getStatus(),
                "username", username
        ));
    }
}
