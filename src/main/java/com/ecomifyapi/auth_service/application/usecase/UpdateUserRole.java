package com.ecomifyapi.auth_service.application.usecase;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;
import com.ecomifyapi.auth_service.domain.entities.User;

public class UpdateUserRole {
    private final UserGateway userGateway;

    public UpdateUserRole(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User update (User user, Long id) {
        return userGateway.updateRole(user, id);
    }
}
