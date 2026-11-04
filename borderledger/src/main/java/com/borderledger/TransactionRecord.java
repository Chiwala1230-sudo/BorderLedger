package com.borderledger;

import java.time.LocalDateTime;

public class TransactionRecord {

    private final String transactionId;
    private final String traderId;
    private final double amount;
    private final LocalDateTime date;

    public TransactionRecord(
            String transactionId,
            String traderId,
            double amount,
            LocalDateTime date) {

        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Transaction ID cannot be empty.");
        }

        if (traderId == null || traderId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Trader ID cannot be empty.");
        }

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Transaction amount cannot be negative.");
        }

        if (date == null) {
            throw new IllegalArgumentException(
                    "Transaction date cannot be null.");
        }

        this.transactionId = transactionId;
        this.traderId = traderId;
        this.amount = amount;
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTraderId() {
        return traderId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}