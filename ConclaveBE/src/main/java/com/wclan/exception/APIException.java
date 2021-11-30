package com.wclan.exception;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * base class for our custom exceptions
 * TODO make it so you can add more stuff or something
 */
public abstract class APIException extends RuntimeException {
    protected final Map<String, Object> body;

    public APIException(String message) {
        super(message);
        this.body = new HashMap<>();
        this.body.put("exception", this.getClass().getName());
        this.body.put("message", message);
        this.body.put("timestamp", Date.from(Instant.now()));
    }

    public Map<String, Object> body() { return this.body; }
}
