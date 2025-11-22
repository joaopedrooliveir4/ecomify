package com.ecomifyapi.auth_service.domain.exception;

public class PasswordArgumentException extends RuntimeException {
    public PasswordArgumentException(String message) {
        super(message);
    }
}
