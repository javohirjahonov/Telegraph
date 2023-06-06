package com.example.telegraphback.config;

import com.example.telegraphback.exceptions.RequestValidationException;
import com.example.telegraphback.exceptions.UniqueException;
import com.example.telegraphback.exceptions.UserCheckException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<String> RequestValidationException(
            RequestValidationException e
    ) {
        System.out.println("e.getMessage() = " + e.getMessage());
        return ResponseEntity.status(500).body(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = {UserCheckException.class})
    public ResponseEntity<String> UserCheckException(
            UserCheckException e
    ) {
        System.out.println("e.getMessage() = " + e.getMessage());
        return ResponseEntity.status(401).body(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = {UniqueException.class})
    public ResponseEntity<String> UniqueException(
            UniqueException e
    ) {
        System.out.println("e.getMessage() = " + e.getMessage());
        return ResponseEntity.status(500).body(e.getLocalizedMessage());
    }

}
