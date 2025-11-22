package com.ecomifyapi.auth_service.domain.exception;

public class EmailArgumentException extends RuntimeException {
    public EmailArgumentException(String message) {
        super(message);
    }
}
