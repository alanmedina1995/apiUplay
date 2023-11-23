package com.example.apiuplay.models.views;

public class TransactionDTO {

    private Long id;
    private Long userId;
    private double amount;
    private double currentDollarBlueValue;
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