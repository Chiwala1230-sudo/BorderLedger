package com.borderledger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TraderTest {

    @Test
    public void testInformalTraderScoreCalculation() {
        // 1. Create a trader (minimum 3 transactions required)
        InformalTrader trader = new InformalTrader("T01", "Test Trader", 1000.0);
        
        // 2. Add valid transactions
        trader.addTransaction(100.0);
        trader.addTransaction(200.0);
        trader.addTransaction(300.0);
        
        // 3. Calculate score (should succeed)
        assertDoesNotThrow(() -> {
            double score = trader.calculateDocumentScore();
            assertTrue(score > 0); 
        });
    }

    @Test
    public void testInsufficientHistoryException() {
        // 1. Create a trader
        InformalTrader trader = new InformalTrader("T02", "Test Trader 2", 500.0);
        
        // 2. Only add 1 transaction (minimum is 3, so this should fail)
        trader.addTransaction(50.0); 
        
        // 3. Check that our custom exception is thrown correctly
        assertThrows(InsufficientHistoryException.class, () -> {
            trader.calculateDocumentScore();
        });
    }
}