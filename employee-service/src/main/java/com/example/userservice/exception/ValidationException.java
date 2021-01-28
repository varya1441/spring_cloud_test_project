package com.example.userservice.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}