package com.dubem.monieshop.utils;

import com.dubem.monieshop.service.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

public class FileProcesssorScheduler {
    private final TransactionService transactionService;

    public FileProcesssorScheduler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Scheduled(cron = "0 0 1 * * ?") // Runs at 1 AM daily
    public void processDailyTransactions() {
        File folder = new File("path_to_transaction_files");
        for (File file : folder.listFiles()) {
            try {
                transactionService.processFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
