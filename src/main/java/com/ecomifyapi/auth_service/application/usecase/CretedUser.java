package com.ecomifyapi.auth_service.application.usecase;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;
import com.ecomifyapi.auth_service.domain.entities.User;

public class CretedUser {
    private final UserGateway userGateway;

    public CretedUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User create (User user) {
        return userGateway.save(user);
    }
}