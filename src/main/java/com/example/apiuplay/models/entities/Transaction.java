package com.example.apiuplay.models.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Getter
    @Column(name = "utn_amount", nullable = false)
    private double amount;

    @Getter
    @Column(name = "current_dollar_blue_value", nullable = false)
    private double currentDollarBlueValue;

    @Column(name = "cryptocurrency", nullable = false)
    private String cryptocurrency;

    @Column(name = "crypto_amount", nullable = false)
    private double cryptoAmount;

    @Getter
    @Column(name = "current_crypto_value", nullable = false)
    private double currentCryptoValue;


    public Transaction() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public void setCurrentDollarBlueValue(double currentDollarBlueValue) {
        this.currentDollarBlueValue = currentDollarBlueValue;
    }

    public void setCurrentCryptoValue(double currentCryptoValue) {
        this.currentCryptoValue = currentCryptoValue;
    }
}