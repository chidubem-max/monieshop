package com.dubem.monieshop.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int salesStaffId;

    private LocalDateTime transactionTime;

    private double saleAmount;

    @ElementCollection
    private Map<Long, Integer> productsSold;


    public Transaction() {}



    public Transaction(int salesStaffId, LocalDateTime time, double saleAmount, Map<Long, Integer> products) {
        this.salesStaffId = salesStaffId;
        this.transactionTime = time;
        this.saleAmount = saleAmount;
        this.productsSold = products;
    }




    public Map<Long, Integer> getProductsSold() {
        return productsSold;
    }



}
