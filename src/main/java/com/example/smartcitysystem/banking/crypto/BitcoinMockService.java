package com.example.smartcitysystem.banking.crypto;

import java.util.UUID;

public class BitcoinMockService implements CryptoPaymentAdapter {

    @Override
    public CryptoResult pay(String username, double amount, String service) {
        return new CryptoResult("BTC", "SUCCESS", "btc_" + UUID.randomUUID());
    }
}
