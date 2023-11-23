package com.example.apiuplay.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "current_dollar_blue_value", nullable = false)
    private double currentDollarBlueValue;

    @Column(name = "current_crypto_value", nullable = false)
    private double currentCryptoValue;


    public Transaction(Long id, User user, LocalDateTime transactionDate, double amount, double currentDollarBlueValue, double currentCryptoValue) {
        this.id = id;
        this.user = user;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.currentDollarBlueValue = currentDollarBlueValue;
        this.currentCryptoValue = currentCryptoValue;
    }


    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCurrentDollarBlueValue() {
        return currentDollarBlueValue;
    }

    public void setCurrentDollarBlueValue(double currentDollarBlueValue) {
        this.currentDollarBlueValue = currentDollarBlueValue;
    }

    public double getCurrentCryptoValue() {
        return currentCryptoValue;
    }

    public void setCurrentCryptoValue(double currentCryptoValue) {
        this.currentCryptoValue = currentCryptoValue;
    }
}