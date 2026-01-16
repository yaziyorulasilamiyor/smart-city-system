package com.example.smartcitysystem.banking.crypto;

public interface CryptoPaymentAdapter {
    CryptoResult pay(String username, double amount, String service);
}