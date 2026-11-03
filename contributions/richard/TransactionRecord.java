package com.borderledger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents one transaction made by a trader.
 *
 * Example: a trader sells goods worth K250 on a particular date.
 * The class stores the details of that one transaction.
 */
public class TransactionRecord {

    // These fields are private so they cannot be changed directly from outside.
    private final String transactionId;
    private final BigDecimal amount;
    private final LocalDate transactionDate;
    private final String description;

    /**
     * Creates one transaction record.
     *
     * BigDecimal is used for money because it avoids many rounding problems
     * that can happen when using double for financial amounts.
     */
    public TransactionRecord(String transactionId, BigDecimal amount,
                             LocalDate transactionDate, String description) {

        this.transactionId = requireText(transactionId, "Transaction ID");

        // A transaction amount must exist and must be greater than zero.
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        this.transactionDate = Objects.requireNonNull(
                transactionDate, "Transaction date cannot be null");

        this.description = requireText(description, "Description");
    }

    // Getters allow other classes to read the transaction details safely.
    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getDescription() {
        return description;
    }

    // Checks that text values are supplied and are not just spaces.
    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
