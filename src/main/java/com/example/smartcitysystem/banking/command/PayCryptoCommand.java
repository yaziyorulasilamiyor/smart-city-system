package com.example.smartcitysystem.banking.command;

import com.example.smartcitysystem.banking.crypto.CryptoResult;
import com.example.smartcitysystem.banking.dto.CryptoPayRequest;
import com.example.smartcitysystem.banking.service.CryptoPaymentService;

public class PayCryptoCommand implements Command<CryptoResult> {

    private final CryptoPaymentService cryptoPaymentService;
    private final String username;
    private final CryptoPayRequest req;

    public PayCryptoCommand(CryptoPaymentService cryptoPaymentService, String username, CryptoPayRequest req) {
        this.cryptoPaymentService = cryptoPaymentService;
        this.username = username;
        this.req = req;
    }

    @Override
    public CryptoResult execute() {
        return cryptoPaymentService.payCrypto(username, req);
    }
}
