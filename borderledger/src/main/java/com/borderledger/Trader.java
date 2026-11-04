package com.borderledger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Trader {
    
    private final String id; 
    private String name;
    private final List<Double> transactions;

    public Trader(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Trader ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Trader name cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.transactions = new ArrayList<>(); 
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Double> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void addTransaction(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative.");
        }
        this.transactions.add(amount);
    }

    // 🟢 NEW HELPER METHOD: This allows subclasses to see the total volume safely.
    protected double getTotalTransactionVolume() {
        double total = 0;
        for (double amount : transactions) {
            total += amount;
        }
        return total;
    }

    // 🟢 NEW HELPER METHOD: This allows subclasses to throw the exception if they don't have enough history.
    protected void requireMinimumHistory(int minTransactions) throws InsufficientHistoryException {
        if (transactions.size() < minTransactions) {
            throw new InsufficientHistoryException("Not enough transactions.");
        }
    }

    public abstract double calculateDocumentScore() throws InsufficientHistoryException;
}