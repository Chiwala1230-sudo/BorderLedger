package com.borderledger;

/**
 * A custom error used when a trader does not have enough transactions
 * for the system to calculate a reliable score.
 *
 * This is a checked exception because it extends Exception.
 * That means code using calculateScore() must handle it or declare it.
 */
public class InsufficientHistoryException extends Exception {

    // A message explains what went wrong, for example how many records are missing.
    public InsufficientHistoryException(String message) {
        super(message);
    }
}
