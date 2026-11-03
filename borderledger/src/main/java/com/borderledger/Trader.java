package com.borderledger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// ABSTRACT CLASS: Cannot be instantiated directly (no 'new Trader()'). It exists to be inherited by the 3 subclasses.
public abstract class Trader {
    
    // ENCAPSULATION: Fields are 'private'. No one outside this class can change them directly.
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
        this.transactions = new ArrayList<>(); // Initialize the list
    }

    // --- GETTERS (The only way to read the private fields) ---
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Defensive copy: returns an unmodifiable list so outside code can't sneak in and delete transactions.
    public List<Double> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    // Concrete method: All traders share this behavior.
    public void addTransaction(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative.");
        }
        this.transactions.add(amount);
    }

    // ABSTRACT METHOD: The "Blank Section of the Form". 
    // Every subclass (Informal, SemiFormal, Cooperative) MUST write its own version of this.
    // It throws our Custom Exception if the trader hasn't logged enough sales yet.
    public abstract double calculateDocumentScore() throws InsufficientHistoryException;
}