package com.dubem.monieshop.repository;

import com.dubem.monieshop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionTimeBetween(LocalDate start, LocalDate end);
}
