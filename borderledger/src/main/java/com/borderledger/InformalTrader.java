package com.borderledger;

public class InformalTrader extends Trader {
    private static final int MIN_TRANSACTIONS = 3;
    private static final double CAPITAL_WEIGHT = 0.1;
    private static final double VOLUME_WEIGHT = 0.05;

    public InformalTrader(String traderId, String traderName, double startingCapital) {
        super(traderId, traderName, startingCapital);
    }

    @Override
    public double calculateScore() throws InsufficientHistoryException {
        requireMinimumHistory(MIN_TRANSACTIONS);
        return (getStartingCapital() * CAPITAL_WEIGHT) + (getTotalTransactionVolume() * VOLUME_WEIGHT);
    }
}