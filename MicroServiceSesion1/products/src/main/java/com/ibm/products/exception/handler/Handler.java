package com.ibm.products.exception.handler;

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
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e){
        final Map<String, Object> body = new HashMap<>();
        body.put("time", LocalDateTime.now());
        body.put("message", e.getReason());
        body.put("status", e.getStatusCode().value());
        return new ResponseEntity<>(body, e.getStatusCode());
    }
}
