package com.wclan.exception;

public abstract class APIException extends RuntimeException {
    private final String message;

    public APIException(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}
