package com.wclan.exception;

import com.wclan.exception.BadResourceException;
import com.wclan.exception.ResourceAlreadyExistsException;
import com.wclan.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Manages exceptions thrown by the application globally.
 */
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    protected ResponseEntity<Object> handleConflict(ResourceAlreadyExistsException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return super.handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, "hi",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BadResourceException.class)
    protected ResponseEntity<Object> handleBadResource(BadResourceException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, "hello",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}