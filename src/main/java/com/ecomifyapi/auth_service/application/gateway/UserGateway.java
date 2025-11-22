package com.ecomifyapi.auth_service.application.gateway;

import com.ecomifyapi.auth_service.domain.entities.User;

import java.util.Optional;

public interface UserGateway {
    User save (User user);
    Optional<User> findByEmail (User user);
}
