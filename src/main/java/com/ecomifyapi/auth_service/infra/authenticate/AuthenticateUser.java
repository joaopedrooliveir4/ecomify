package com.ecomifyapi.auth_service.infra.authenticate;

public interface AuthenticateUser {
    String authenticate(String email, String password);
}