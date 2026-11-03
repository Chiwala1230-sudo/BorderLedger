package com.borderledger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The shared parent class for every type of trader in the project.
 *
 * In simple terms:
 * - Every trader has an ID, a name, a location and a transaction history.
 * - The class keeps those shared details in one place.
 * - A specific trader type (for example, InformalTrader) will extend this class
 *   and provide its own version of calculateScore().
 */
public abstract class Trader {

    // "private" means other classes cannot change these fields directly.
    // This protects the data and makes us use the methods below instead.
    private final String traderId; // final means the ID cannot be changed after creation.
    private String traderName;
    private String location;

    // A List is a collection that stores items in order.
    // Each item here is one transaction belonging to this trader.
    private final List<TransactionRecord> transactionHistory;

    // This is a project design choice, not a number stated in the assignment brief.
    // It means a trader needs at least five transactions before a score is calculated.
    public static final int MINIMUM_TRANSACTION_HISTORY = 5;

    /**
     * Creates a trader and stores the details shared by all trader types.
     *
     * @param traderId unique ID for this trader
     * @param traderName trader's name
     * @param location trader's operating location
     */
    protected Trader(String traderId, String traderName, String location) {
        // Check the details before saving them. This prevents blank or missing values.
        this.traderId = requireText(traderId, "Trader ID");
        this.traderName = requireText(traderName, "Trader name");
        this.location = requireText(location, "Location");

        // Start with an empty history. Transactions are added later.
        this.transactionHistory = new ArrayList<>();
    }

    // A getter lets other classes read the ID without changing it.
    public String getTraderId() {
        return traderId;
    }

    public String getTraderName() {
        return traderName;
    }

    // A setter allows the name to be updated safely.
    public void setTraderName(String traderName) {
        this.traderName = requireText(traderName, "Trader name");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = requireText(location, "Location");
    }

    /**
     * Returns the transaction history as read-only data.
     * Other classes can view the list, but cannot add/remove items through it.
     */
    public List<TransactionRecord> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    // Tells us how many transactions have been recorded.
    public int getTransactionCount() {
        return transactionHistory.size();
    }

    /**
     * Adds one transaction to this trader's history.
     * The transaction must not be null.
     */
    public void addTransaction(TransactionRecord transaction) {
        Objects.requireNonNull(transaction, "Transaction cannot be null");
        transactionHistory.add(transaction);
    }

    /**
     * Checks whether enough transactions exist before calculating a score.
     * If there are too few, Java throws our custom exception.
     */
    protected void checkTransactionHistory() throws InsufficientHistoryException {
        checkTransactionHistory(MINIMUM_TRANSACTION_HISTORY);
    }

    /**
     * Same check, but lets a child class request a different minimum if the
     * group later agrees on a different rule.
     */
    protected void checkTransactionHistory(int minimumRequired)
            throws InsufficientHistoryException {

        if (minimumRequired < 0) {
            throw new IllegalArgumentException("Minimum history cannot be negative.");
        }

        if (getTransactionCount() < minimumRequired) {
            throw new InsufficientHistoryException(
                    "This trader has " + getTransactionCount()
                    + " transaction(s), but at least " + minimumRequired
                    + " are required to calculate a score."
            );
        }
    }

    /**
     * Every child class must explain how its own score is calculated.
     * This method is abstract because the scoring rules differ by trader type.
     */
    public abstract double calculateScore() throws InsufficientHistoryException;

    // A small helper to make sure required text values are not blank.
    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
