package com.wclan.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * base class for our custom exceptions
 * TODO make it so you can add more stuff or something
 */
public abstract class APIException extends RuntimeException {
    private final Map<String, String> body;
    private final String message;

    public APIException(String message) {
        super(message);
        this.message = message;
        this.body = new HashMap<>();
    }

    public void addToBody(String key, String value) {
        this.body.put(key, value);
    }
}
