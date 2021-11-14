package com.wclan.exception;

/**
 * Custom exception.
 * Thrown when trying to POST a resource that already exists in the repository.
 */
public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
