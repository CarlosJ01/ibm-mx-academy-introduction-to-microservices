package com.ibm.warehouse.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException exception){
        final Map<String, Object> body = new HashMap<>();
        body.put("time", LocalDateTime.now());
        body.put("message", exception.getReason());
        body.put("status", exception.getStatusCode().value());
        return new ResponseEntity<>(body, exception.getStatusCode());
    }
}
