package com.borderledger;

// This is a Custom Exception. We extend 'Exception' so Java treats it as a throwable error.
public class InsufficientHistoryException extends Exception {
    
    public InsufficientHistoryException(String message) {
        // 'super(message)' passes the error message up to the parent Exception class.
        super(message);
    }
}