package com.borderledger;

import java.time.LocalDateTime;

public class TransactionRecord {
    private String transactionId;
    private String traderId;
    private double amount;
    private LocalDateTime date;

    public TransactionRecord(String transactionId, String traderId, double amount, LocalDateTime date) {
        this.transactionId = transactionId;
        this.traderId = traderId;
        this.amount = amount;
        this.date = date;
    }

    public String getTransactionId() { return transactionId; }
    public String getTraderId() { return traderId; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
}