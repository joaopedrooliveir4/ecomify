package com.ecomifyapi.auth_service.application.usecase;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;
import com.ecomifyapi.auth_service.domain.entities.User;

public class UpdatePassword {
    private final UserGateway userGateway;

    public UpdatePassword(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User update (String newPassword, Long id) {
        return userGateway.updatePassword(newPassword, id);
    }
}
