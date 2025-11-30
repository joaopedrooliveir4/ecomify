package com.ecomifyapi.auth_service.application.usecase;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;

public class DeleteUser {
    private final UserGateway userGateway;

    public DeleteUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void delete (Long id) {
        userGateway.deleteUser(id);
    }
}
