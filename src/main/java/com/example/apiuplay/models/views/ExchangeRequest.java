package com.example.apiuplay.models.views;

import lombok.Getter;

public class ExchangeRequest {
    private Long userId;
    private double amount;
    private double currentDollarBlueValue;
    private double currentCryptoValue;
    @Getter
    private String cryptocurrency;

    private double cryptoAmount;

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

    public void setCryptocurrency(String cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }
}