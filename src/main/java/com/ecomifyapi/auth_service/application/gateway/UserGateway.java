package com.ecomifyapi.auth_service.application.gateway;

import com.ecomifyapi.auth_service.domain.entities.User;

import java.util.Optional;

public interface UserGateway {
    User save (User user);
    User updateRole (User user, Long id);
    Optional<User> findByEmail (User user);
}
