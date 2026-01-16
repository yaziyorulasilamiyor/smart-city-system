package com.example.smartcitysystem.banking.crypto;

import java.util.UUID;

public class EthereumMockService implements CryptoPaymentAdapter {

    @Override
    public CryptoResult pay(String username, double amount, String service) {
        return new CryptoResult("ETH", "SUCCESS", "eth_" + UUID.randomUUID());
    }
}
