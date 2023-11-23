package com.example.apiuplay.services;

import com.example.apiuplay.models.entities.Transaction;
import com.example.apiuplay.models.entities.User;
import com.example.apiuplay.models.views.TransactionDTO;
import com.example.apiuplay.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(User user, double amount, double currentDollarBlueValue, double currentCryptoValue, String cryptocurrency, double cryptoAmount) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setCurrentDollarBlueValue(currentDollarBlueValue);
        transaction.setCurrentCryptoValue(currentCryptoValue);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCryptocurrency(cryptocurrency);
        transaction.setCryptoAmount(cryptoAmount);
        transactionRepository.save(transaction);
        System.out.println("Transaction saved: " + transaction.getId());
    }


    public List<TransactionDTO> getUserTransactions(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        BeanUtils.copyProperties(transaction, dto);
        return dto;
    }
}
