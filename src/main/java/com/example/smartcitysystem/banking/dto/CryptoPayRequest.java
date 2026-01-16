package com.example.smartcitysystem.banking.dto;

public class CryptoPayRequest {
    private double amount;
    private String currency; // "BTC" / "ETH" cyrpto para
    private String service;  // "PARKING" vs.

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
}

