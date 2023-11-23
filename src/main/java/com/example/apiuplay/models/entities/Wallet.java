package com.example.apiuplay.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "utncoin_amount")
    private Double utncoinAmount;

    @Column(name = "bitcoin_amount")
    private Double bitcoinAmount;

    @Column(name = "monero_amount")
    private Double moneroAmount;

    @Column(name = "ethereum_amount")
    private Double ethereumAmount;

    public Wallet(User user, double utncoinAmount, double bitcoinAmount, double moneroAmount, double ethereumAmount) {
        this.user = user;
        this.utncoinAmount = utncoinAmount;
        this.bitcoinAmount = bitcoinAmount;
        this.moneroAmount = moneroAmount;
        this.ethereumAmount = moneroAmount;
    }

    public Wallet(double utncoinAmount, double bitcoinAmount, double moneroAmount, double ethereumAmount) {
        this.utncoinAmount = utncoinAmount;
        this.bitcoinAmount = bitcoinAmount;
        this.moneroAmount = moneroAmount;
        this.ethereumAmount = ethereumAmount;
    }
}
