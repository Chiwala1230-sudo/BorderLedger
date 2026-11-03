package com.borderledger;

import java.util.ArrayList;
import java.util.List;

public abstract class Trader {
    private final String id;
    private String name;
    private double startingCapital; // Added if your subclasses use startingCapital
    protected List<Double> transactions;

    public Trader(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Trader ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Trader name cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.startingCapital = 0.0; // Default or can be passed via overloaded constructor
        this.transactions = new ArrayList<>();
    }

    public Trader(String id, String name, double startingCapital) {
        this(id, name);
        if (startingCapital < 0) {
            throw new IllegalArgumentException("Starting capital cannot be negative.");
        }
        this.startingCapital = startingCapital;
    }

    // --- HELPER METHOD ---
    protected double getTotalTransactionVolume() {
        double total = 0;
        for (double amount : transactions) {
            total += amount;
        }
        return total;
    }

    // Abstract method that subclasses must implement
    public abstract double calculateScore() throws InsufficientHistoryException;

    // Validation helper for minimum history
    protected void requireMinimumHistory(int minTransactions) throws InsufficientHistoryException {
        if (transactions.size() < minTransactions) {
            throw new InsufficientHistoryException("Insufficient transaction history. Required: " + minTransactions + ", Found: " + transactions.size());
        }
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartingCapital() {
        return startingCapital;
    }

    public void setStartingCapital(double startingCapital) {
        if (startingCapital < 0) {
            throw new IllegalArgumentException("Starting capital cannot be negative.");
        }
        this.startingCapital = startingCapital;
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative.");
        }
        transactions.add(amount);
    }
}