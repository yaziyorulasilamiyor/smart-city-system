package com.example.smartcitysystem.banking.crypto;

public class CryptoAdapterFactory {

    private static final CryptoAdapterFactory INSTANCE = new CryptoAdapterFactory();

    private CryptoAdapterFactory() {}

    public static CryptoAdapterFactory getInstance() {
        return INSTANCE;
    }

    public CryptoPaymentAdapter of(String currency) {
        if (currency == null) throw new IllegalArgumentException("currency is null");

        return switch (currency.toUpperCase()) {
            case "BTC" -> new BitcoinMockService();
            case "ETH" -> new EthereumMockService();
            default -> throw new IllegalArgumentException("Unsupported currency: " + currency);
        };
    }
}
