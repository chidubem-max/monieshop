package com.dubem.monieshop.controller;

import com.dubem.monieshop.model.Transaction;
import com.dubem.monieshop.repository.TransactionRepository;
import lombok.Value;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO for {@link com.dubem.monieshop.model.Transaction}
 */
@Value
public class AnalyticsService  {
    private final TransactionRepository repository;

    public AnalyticsService(TransactionRepository repository) {

        this.repository = repository;
    }

    @Cacheable("highestSalesVolumeDay")
    public long highestSalesVolumeDay() {
        return repository.findAll().stream()
                .mapToLong(tx -> tx.getProductsSold().values().stream().mapToInt(Integer::intValue).sum())
                .max()
                .orElse(0);
    }

    @Cacheable("highestSalesValueDay")
    public double highestSalesValueDay() {
        return repository.findAll().stream()
                .mapToDouble(Transaction::getSaleAmount)
                .max()
                .orElse(0.0);
    }

    public Double getHighestSalesValueInDay() {
    }
}