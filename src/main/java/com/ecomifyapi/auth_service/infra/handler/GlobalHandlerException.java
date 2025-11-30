package com.ecomifyapi.auth_service.infra.handler;

import com.ecomifyapi.auth_service.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
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
                HttpStatus.BAD_REQUEST.value(),
                message
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailArgumentException.class)
    public ResponseEntity<ApiError> emailHandlerException (EmailArgumentException e) {
        Map<String, String> message = new HashMap<>();
        message.put("Error:", e.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                message
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordArgumentException.class)
    public ResponseEntity<ApiError> senhaHandlerException (PasswordArgumentException e) {
        Map<String, String> message = new HashMap<>();
        message.put("Error:", e.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                message
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(
            UserNotFoundException ex
    ) {
        Map<String, String> message = new HashMap<>();
        message.put("error", ex.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                message
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
