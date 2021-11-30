package com.wclan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception.
 * Thrown when input is formatted incorrectly for whatever reason.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadResourceException extends APIException {
    // maybe add custom reasons
    public BadResourceException(String msg) {
        super(msg);
        this.body.put("status", HttpStatus.BAD_REQUEST);
    }
}
