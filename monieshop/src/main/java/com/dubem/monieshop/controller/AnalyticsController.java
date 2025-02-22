package com.dubem.monieshop.controller;

import com.dubem.monieshop.model.Transaction;
import com.dubem.monieshop.service.TransactionService;
import jakarta.persistence.criteria.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {


    private AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("\"/highest-sales-volume-day\"")
    public long getHighestSalesVolumeDay() {
        return analyticsService.highestSalesVolumeDay();
    }

    @GetMapping("\"/highest-sales-value-day\"")
    public double getHighestSalesValueDay() {
        return analyticsService.highestSalesValueDay();
    }

}
