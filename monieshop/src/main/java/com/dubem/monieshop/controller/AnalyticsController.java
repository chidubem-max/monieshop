package com.dubem.monieshop.controller;

import com.dubem.monieshop.model.Transaction;
import com.dubem.monieshop.service.TransactionService;
import jakarta.persistence.criteria.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class AnalyticsController {

    private final TransactionService transactionService;

    public AnalyticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/highest_sales_day")
    public Map<LocalDate, Double> highestSalesDay() throws IOException {
        Path filePath = (Path) Paths.get("transactions.txt");
        List<Transaction> transactions = transactionService.readTransactionsFromFile(filePath);
        return transactionService.getHighestSalesValuePerDay(transactions);
    }
}
