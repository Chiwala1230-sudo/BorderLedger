package com.borderledger;

public class SemiFormalTrader extends Trader {
    private static final int MIN_TRANSACTIONS = 5;    // TODO
    private static final double CAPITAL_WEIGHT = 0.2; // TODO
    private static final double VOLUME_WEIGHT = 0.05; // TODO
    private static final double TPIN_BONUS = 50.0;    // TODO

    private double startingCapital;
    private int monthsActive;
    private boolean hasTpin;

    public SemiFormalTrader(String id, String name, double startingCapital,
                            int monthsActive, boolean hasTpin) {
        super(id, name);
        setStartingCapital(startingCapital);
        setMonthsActive(monthsActive);
        this.hasTpin = hasTpin;
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

    public int getMonthsActive() {
        return monthsActive;
    }

    public final void setMonthsActive(int monthsActive) {
        if (monthsActive < 0) {
            throw new IllegalArgumentException("Months active cannot be negative.");
        }
        this.monthsActive = monthsActive;
    }

    public boolean hasTpin() {
        return hasTpin;
    }

    public void setHasTpin(boolean hasTpin) {
        this.hasTpin = hasTpin;
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
        double score = startingCapital * CAPITAL_WEIGHT
                + volume * VOLUME_WEIGHT
                + (monthsActive * 10.0);
        return hasTpin ? score + TPIN_BONUS : score;
    }
}