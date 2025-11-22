package com.ecomifyapi.auth_service.infra.handler;

import com.ecomifyapi.auth_service.domain.exception.ApiError;
import com.ecomifyapi.auth_service.domain.exception.EmailArgumentException;
import com.ecomifyapi.auth_service.domain.exception.NameArgumentException;
import com.ecomifyapi.auth_service.domain.exception.PasswordArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NameArgumentException.class)
    public ResponseEntity<ApiError> nameHandlerException (NameArgumentException e) {
        Map<String, String> message = new HashMap<>();
        message.put("Error:", e.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                message
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmailArgumentException.class)
    public ResponseEntity<ApiError> emailHandlerException (EmailArgumentException e) {
        Map<String, String> message = new HashMap<>();
        message.put("Error:", e.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                message
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PasswordArgumentException.class)
    public ResponseEntity<ApiError> senhaHandlerException (PasswordArgumentException e) {
        Map<String, String> message = new HashMap<>();
        message.put("Error:", e.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                message
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}
