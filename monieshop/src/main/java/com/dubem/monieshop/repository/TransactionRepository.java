package com.dubem.monieshop.repository;

import com.dubem.monieshop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionTimeDate(LocalDate date);
    List<Transaction> findByTransactionTimeBetween(LocalDate start, LocalDate end);
}
