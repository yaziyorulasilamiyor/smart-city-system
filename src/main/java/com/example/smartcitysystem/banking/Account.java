package com.example.smartcitysystem.banking;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private double fiatBalance;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public double getFiatBalance() { return fiatBalance; }
    public void setFiatBalance(double fiatBalance) { this.fiatBalance = fiatBalance; }
}
