package com.example.smartcitysystem.banking.dto;

public class CryptoPayResponse {
    private String status;
    private String currency; // "BTC" şu an
    private double amount;
    private String service;
    private String txHash;
    private String username;

    public CryptoPayResponse(String status, String currency, double amount, String service, String txHash, String username) {
        this.status = status;
        this.currency = currency;
        this.amount = amount;
        this.service = service;
        this.txHash = txHash;
        this.username = username;
    }

    public String getStatus() { return status; }
    public String getCurrency() { return currency; }
    public double getAmount() { return amount; }
    public String getService() { return service; }
    public String getTxHash() { return txHash; }
    public String getUsername() { return username; }
}
