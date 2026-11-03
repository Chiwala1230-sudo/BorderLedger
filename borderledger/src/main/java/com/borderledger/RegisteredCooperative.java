package com.borderledger;

public class RegisteredCooperative extends Trader {
    private static final int MIN_TRANSACTIONS = 5;
    private static final double CAPITAL_WEIGHT = 0.35;
    private static final double VOLUME_WEIGHT = 0.05;
    private static final double MEMBER_WEIGHT = 15.0;
    private static final double COMPLIANCE_WEIGHT = 20.0;

    private int memberCount;
    private double complianceRating; // 0.0 to 5.0

    public RegisteredCooperative(String traderId, String traderName, double startingCapital,
                                 int memberCount, double complianceRating) {
        super(traderId, traderName, startingCapital);
        setMemberCount(memberCount);
        setComplianceRating(complianceRating);
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
    public double calculateScore() throws InsufficientHistoryException {
        requireMinimumHistory(MIN_TRANSACTIONS);
        return (getStartingCapital() * CAPITAL_WEIGHT)
                + (getTotalTransactionVolume() * VOLUME_WEIGHT)
                + (memberCount * MEMBER_WEIGHT)
                + (complianceRating * COMPLIANCE_WEIGHT);
    }
}