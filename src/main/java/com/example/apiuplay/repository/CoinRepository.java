package com.example.apiuplay.repository;

import com.example.apiuplay.models.entities.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
}
