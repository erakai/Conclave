package com.wclan.exception;

/**
 * Custom exception.
 * Thrown when input is formatted incorrectly for whatever reason.
 */
public class BadResourceException extends Exception {
    // maybe add custom reasons
    public BadResourceException(String msg) {
        super(msg);
    }
}
