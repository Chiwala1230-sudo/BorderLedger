package com.borderledger;

public class InformalTrader extends Trader {
    private static final int MIN_TRANSACTIONS = 3;    // TODO: brief's value
    private static final double CAPITAL_WEIGHT = 0.1; // TODO
    private static final double VOLUME_WEIGHT = 0.05; // TODO

    private double startingCapital;

    public InformalTrader(String id, String name, double startingCapital) {
        super(id, name);
        setStartingCapital(startingCapital);
    }

    public double getStartingCapital() {
        return startingCapital;
    }

    public final void setStartingCapital(double startingCapital) {
        if (startingCapital < 0) {
            throw new IllegalArgumentException("Starting capital cannot be negative.");
        }
        this.startingCapital = startingCapital;
    }

    @Override
    public double calculateDocumentScore() throws InsufficientHistoryException {
        if (getTransactions().size() < MIN_TRANSACTIONS) {
            throw new InsufficientHistoryException(
                    "Insufficient transaction history. Required: " + MIN_TRANSACTIONS
                            + ", Found: " + getTransactions().size());
        }
        double volume = 0;
        for (double amount : getTransactions()) {
            volume += amount;
        }
        return startingCapital * CAPITAL_WEIGHT + volume * VOLUME_WEIGHT;
    }
}