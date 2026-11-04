package com.borderledger;

public class RegisteredCooperative extends Trader {
    private static final int MIN_TRANSACTIONS = 5;        // TODO
    private static final double CAPITAL_WEIGHT = 0.35;    // TODO
    private static final double VOLUME_WEIGHT = 0.05;     // TODO
    private static final double MEMBER_WEIGHT = 15.0;     // TODO
    private static final double COMPLIANCE_WEIGHT = 20.0; // TODO

    private double startingCapital;
    private int memberCount;
    private double complianceRating; // 0.0 to 5.0

    public RegisteredCooperative(String id, String name, double startingCapital,
                                 int memberCount, double complianceRating) {
        super(id, name);
        setStartingCapital(startingCapital);
        setMemberCount(memberCount);
        setComplianceRating(complianceRating);
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

    public int getMemberCount() {
        return memberCount;
    }

    public final void setMemberCount(int memberCount) {
        if (memberCount <= 0) {
            throw new IllegalArgumentException("Cooperative must have at least 1 member.");
        }
        this.memberCount = memberCount;
    }

    public double getComplianceRating() {
        return complianceRating;
    }

    public final void setComplianceRating(double complianceRating) {
        if (complianceRating < 0.0 || complianceRating > 5.0) {
            throw new IllegalArgumentException("Compliance rating must be between 0.0 and 5.0.");
        }
        this.complianceRating = complianceRating;
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
        return startingCapital * CAPITAL_WEIGHT
                + volume * VOLUME_WEIGHT
                + memberCount * MEMBER_WEIGHT
                + complianceRating * COMPLIANCE_WEIGHT;
    }
}