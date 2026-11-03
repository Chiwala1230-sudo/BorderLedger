package com.borderledger;

public class SemiFormalTrader extends Trader {
    private static final int MIN_TRANSACTIONS = 5;    // TODO
    private static final double CAPITAL_WEIGHT = 0.2; // TODO
    private static final double VOLUME_WEIGHT = 0.05; // TODO
    private static final double TPIN_BONUS = 50.0;    // TODO: use brief's bonus

    private int monthsActive;
    private boolean hasTpin;

    public SemiFormalTrader(String traderId, String traderName, double startingCapital,
                            int monthsActive, boolean hasTpin) {
        super(traderId, traderName, startingCapital);
        setMonthsActive(monthsActive);
        this.hasTpin = hasTpin;
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
    public double calculateScore() throws InsufficientHistoryException {
        requireMinimumHistory(MIN_TRANSACTIONS);
        double score = getStartingCapital() * CAPITAL_WEIGHT
                + getTotalTransactionVolume() * VOLUME_WEIGHT
                + (monthsActive * 10.0);
        return hasTpin ? score + TPIN_BONUS : score;
    }
}