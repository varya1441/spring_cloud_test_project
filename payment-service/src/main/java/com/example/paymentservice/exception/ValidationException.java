package com.example.paymentservice.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidationException(String msg) {
        super(msg);
    }
}