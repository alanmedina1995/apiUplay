package com.example.apiuplay.models.views;

import java.time.LocalDateTime;


public class TransactionDTO {

    private Long id;
    private Long userId;
    private LocalDateTime transactionDate;
    private double amount;
    private double currentDollarBlueValue;
    private String cryptocurrency;
    private double cryptoAmount;
    private double currentCryptoValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(String cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }

    public double getCurrentCryptoValue() {
        return currentCryptoValue;
    }

    public void setCurrentCryptoValue(double currentCryptoValue) {
        this.currentCryptoValue = currentCryptoValue;
    }
}