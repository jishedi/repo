package com.wexinc.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.MapperFeature;

import java.time.Instant;

public class Transaction {
    @JsonProperty("transactionId")
    private Long transactionId;
    @JsonProperty("purchaseDescription")
    private String purchaseDescription;
    @JsonProperty("transactionDate")
    private Instant transactionDate;
    @JsonProperty("purchaseAmount")
    private Double purchaseAmount;

    public Transaction(Long transactionId, String purchaseDescription,
                       Instant transactionDate, Double purchaseAmount) {
        this.transactionId = transactionId;
        this.purchaseDescription = purchaseDescription;
        this.transactionDate = transactionDate;
        this.purchaseAmount = purchaseAmount;
    }

    // Getters and Setters
    public Long getTransactionId() { return transactionId; }
    public String getPurchaseDescription() { return purchaseDescription; }
    public Instant getTransactionDate() { return transactionDate; }
    public Double getPurchaseAmount() { return purchaseAmount; }
}
