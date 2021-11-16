package com.wclan.exception;

/**
 * Custom exception.
 * Thrown when trying to access a resource that does not exist.
 */
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
