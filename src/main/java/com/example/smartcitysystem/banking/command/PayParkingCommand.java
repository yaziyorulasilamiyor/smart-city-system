package com.example.smartcitysystem.banking.command;

import com.example.smartcitysystem.banking.BankingService;
import com.example.smartcitysystem.banking.Transaction;

public class PayParkingCommand implements Command<Transaction> {

    private final BankingService bankingService;
    private final String username;
    private final double amount;

    public PayParkingCommand(BankingService bankingService, String username, double amount) {
        this.bankingService = bankingService;
        this.username = username;
        this.amount = amount;
    }

    @Override
    public Transaction execute() {
        return bankingService.payParkingFiat(username, amount);
    }
}
