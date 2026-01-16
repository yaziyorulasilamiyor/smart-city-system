package com.example.smartcitysystem.banking;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String username;

    @Column(nullable=false)
    private String service; // PARKING, TRANSIT, etc

    @Column(nullable=false)
    private String currency; // TRY, BTC, ETH (mock)

    @Column(nullable=false)
    private double amount;

    @Column(nullable=false)
    private String status; // SUCCESS, FAILED

    @Column(nullable=false)
    private Instant ts;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getTs() { return ts; }
    public void setTs(Instant ts) { this.ts = ts; }
}
