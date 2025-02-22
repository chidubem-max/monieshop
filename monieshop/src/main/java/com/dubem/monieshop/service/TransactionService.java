package com.dubem.monieshop.service;

import com.dubem.monieshop.model.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionService {
    public List<Transaction> readTransactionsFromFile(Path filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 4) continue;

            int salesStaffId = Integer.parseInt(parts[0]);
            LocalDateTime time = LocalDateTime.parse(parts[1]);
            double saleAmount = Double.parseDouble(parts[3]);

            Map<Long, Integer> products = new HashMap<>();
            String productDetails = parts[2].replaceAll("[\\[\\]]", "");
            for (String product : productDetails.split("\\|")) {
                String[] p = product.split(":");
                products.put(Long.parseLong(p[0]), Integer.parseInt(p[1]));
            }

            transactions.add(new Transaction(salesStaffId, time, saleAmount, products));
        }
        return transactions;
    }

    public Map<LocalDate, Double> getHighestSalesValuePerDay(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionTime().toLocalDate(),
                        Collectors.summingDouble(Transaction::getSaleAmount)
                ));
    }
}
