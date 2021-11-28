package com.wclan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception.
 * Thrown when trying to POST a resource that already exists in the repository.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends APIException {
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
