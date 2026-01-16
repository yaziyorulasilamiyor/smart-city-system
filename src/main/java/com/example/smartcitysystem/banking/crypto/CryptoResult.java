package com.example.smartcitysystem.banking.crypto;

public class CryptoResult {
    private final String currency;
    private final String txHash;
    private final String status;

    public CryptoResult(String currency, String txHash, String status) {
        this.currency = currency;
        this.txHash = txHash;
        this.status = status;
    }

    public String getCurrency() { return currency; }
    public String getTxHash() { return txHash; }
    public String getStatus() { return status; }
}
