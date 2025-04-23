package com.example.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
