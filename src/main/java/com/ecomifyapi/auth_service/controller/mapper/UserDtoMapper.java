package com.ecomifyapi.auth_service.controller.mapper;

import com.ecomifyapi.auth_service.controller.dtos.UserRequest;
import com.ecomifyapi.auth_service.controller.dtos.UserResponse;
import com.ecomifyapi.auth_service.domain.entities.User;

public class UserDtoMapper {
    public UserResponse toResponse (User user) {
        return new UserResponse("Usuário " + user.getName() + " criado com sucesso!");
    }

    public User toUser (UserRequest request) {
        return new User(request.getName(), request.getEmail(), request.getPassword());
    }
}
