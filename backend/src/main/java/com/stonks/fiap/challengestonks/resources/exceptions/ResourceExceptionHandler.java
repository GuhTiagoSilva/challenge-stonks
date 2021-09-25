package com.stonks.fiap.challengestonks.resources.exceptions;

import com.stonks.fiap.challengestonks.services.exceptions.DatabaseException;
import com.stonks.fiap.challengestonks.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException (ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setError("ResourceNotFoundException");
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimestamp(Instant.now());

        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException (DatabaseException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setError("DatabaseException");
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(status.value()).body(error);
    }



}
