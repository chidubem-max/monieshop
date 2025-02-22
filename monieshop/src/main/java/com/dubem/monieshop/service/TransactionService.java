package com.dubem.monieshop.service;

import com.dubem.monieshop.model.Transaction;
import com.dubem.monieshop.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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


    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void processFile(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int salesStaffId = Integer.parseInt(parts[0]);
            LocalDateTime transactionTime = LocalDateTime.parse(parts[1]);

            Map<Long, Integer> products = new HashMap<>();
            String productsData = parts[2].replace("[", "").replace("]", "");
            for (String productEntry : productsData.split("\\|")) {
                String[] productParts = productEntry.split(":");
                products.put(Long.parseLong(productParts[0]), Integer.parseInt(productParts[1]));
            }

            double saleAmount = Double.parseDouble(parts[3]);

            Transaction transaction = new Transaction();



            repository.save(transaction);
        }
        br.close();
    }
}

