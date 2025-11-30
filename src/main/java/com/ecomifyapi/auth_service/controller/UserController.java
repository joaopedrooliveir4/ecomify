package com.ecomifyapi.auth_service.controller;

import com.ecomifyapi.auth_service.application.usecase.CretedUser;
import com.ecomifyapi.auth_service.application.usecase.DeleteUser;
import com.ecomifyapi.auth_service.application.usecase.UpdatePassword;
import com.ecomifyapi.auth_service.application.usecase.UpdateUserRole;
import com.ecomifyapi.auth_service.controller.dtos.UserRequest;
import com.ecomifyapi.auth_service.controller.dtos.UserResponse;
import com.ecomifyapi.auth_service.controller.integrationDTO.UpdatePasswordRequest;
import com.ecomifyapi.auth_service.controller.mapper.UserDtoMapper;
import com.ecomifyapi.auth_service.controller.producer.AuthProducer;
import com.ecomifyapi.auth_service.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ecomifyapi/user")
public class UserController {
    private final CretedUser cretedUser;
    private final UpdateUserRole updateUserRole;
    private final UserDtoMapper userDtoMapper;
    private final AuthProducer authProducer;
    private final UpdatePassword updatePassword;
    private final DeleteUser deleteUser;

    public UserController(CretedUser cretedUser, UpdateUserRole updateUserRole, UserDtoMapper userDtoMapper, AuthProducer authProducer, UpdatePassword updatePassword, DeleteUser deleteUser) {
        this.cretedUser = cretedUser;
        this.updateUserRole = updateUserRole;
        this.userDtoMapper = userDtoMapper;
        this.authProducer = authProducer;
        this.updatePassword = updatePassword;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    public UserResponse created(@RequestBody UserRequest request) {
        User userBusinessObj = userDtoMapper.toUser(request);
        User user = cretedUser.create(userBusinessObj);

        authProducer.integrar(user);

        return userDtoMapper.toResponse(user);
    }

    @PutMapping("update/role/{id}")
    public User updateRole(@RequestBody User user, @PathVariable Long id) {
        return updateUserRole.update(user, id);
    }

    @PutMapping("update/password/{id}")
    public User updatePassword(@RequestBody UpdatePasswordRequest request,
                               @PathVariable Long id) {
        return updatePassword.update(request.password(), id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        deleteUser.delete(id);
        return ResponseEntity.noContent().build();
    }
}