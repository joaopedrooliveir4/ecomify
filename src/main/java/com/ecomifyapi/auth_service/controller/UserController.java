package com.ecomifyapi.auth_service.controller;

import com.ecomifyapi.auth_service.application.usecase.CretedUser;
import com.ecomifyapi.auth_service.controller.dtos.UserRequest;
import com.ecomifyapi.auth_service.controller.dtos.UserResponse;
import com.ecomifyapi.auth_service.controller.mapper.UserDtoMapper;
import com.ecomifyapi.auth_service.controller.producer.AuthProducer;
import com.ecomifyapi.auth_service.domain.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ecomifyapi/user")
public class UserController {
    private final CretedUser cretedUser;
    private final UserDtoMapper userDtoMapper;
    private final AuthProducer authProducer;

    public UserController(CretedUser cretedUser, UserDtoMapper userDtoMapper, AuthProducer authProducer) {
        this.cretedUser = cretedUser;
        this.userDtoMapper = userDtoMapper;
        this.authProducer = authProducer;
    }

    @PostMapping
    public UserResponse created(@RequestBody UserRequest request) {
        User userBusinessObj = userDtoMapper.toUser(request);
        User user = cretedUser.create(userBusinessObj);

        authProducer.integrar(user);

        return userDtoMapper.toResponse(user);
    }
}
