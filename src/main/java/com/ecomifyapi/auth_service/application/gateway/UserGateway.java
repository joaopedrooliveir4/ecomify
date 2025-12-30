package com.ecomifyapi.auth_service.application.gateway;

import com.ecomifyapi.auth_service.domain.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserGateway {
    User save (User user);
    User updateRole (User user, Long id);
    User updatePassword (String newPassword, Long id);
    ResponseEntity<Void> deleteUser(Long id);
    Optional<User> findByEmail(String email);
}
