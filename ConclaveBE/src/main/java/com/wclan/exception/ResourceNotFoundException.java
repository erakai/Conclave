package com.wclan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception.
 * Thrown when trying to access a resource that does not exist.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends APIException {
    public ResourceNotFoundException(String msg) {
        super(msg);
        this.body.put("status", HttpStatus.NOT_FOUND);
    }
}
