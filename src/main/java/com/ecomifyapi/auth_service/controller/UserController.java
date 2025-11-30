package com.ecomifyapi.auth_service.controller;

import com.ecomifyapi.auth_service.application.usecase.CretedUser;
import com.ecomifyapi.auth_service.application.usecase.UpdateUserRole;
import com.ecomifyapi.auth_service.controller.dtos.UserRequest;
import com.ecomifyapi.auth_service.controller.dtos.UserResponse;
import com.ecomifyapi.auth_service.controller.mapper.UserDtoMapper;
import com.ecomifyapi.auth_service.controller.producer.AuthProducer;
import com.ecomifyapi.auth_service.domain.entities.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ecomifyapi/user")
public class UserController {
    private final CretedUser cretedUser;
    private final UpdateUserRole updateUserRole;
    private final UserDtoMapper userDtoMapper;
    private final AuthProducer authProducer;

    public UserController(CretedUser cretedUser, UpdateUserRole updateUserRole, UserDtoMapper userDtoMapper, AuthProducer authProducer) {
        this.cretedUser = cretedUser;
        this.updateUserRole = updateUserRole;
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

    @PutMapping("update/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        return updateUserRole.update(user, id);
    }
}
