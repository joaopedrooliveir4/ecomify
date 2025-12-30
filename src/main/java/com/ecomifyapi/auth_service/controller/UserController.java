package com.ecomifyapi.auth_service.controller;

import com.ecomifyapi.auth_service.application.usecase.CretedUser;
import com.ecomifyapi.auth_service.application.usecase.DeleteUser;
import com.ecomifyapi.auth_service.application.usecase.UpdatePassword;
import com.ecomifyapi.auth_service.application.usecase.UpdateUserRole;
import com.ecomifyapi.auth_service.controller.dtos.dtoLogin.LoginRequest;
import com.ecomifyapi.auth_service.controller.dtos.dtoLogin.LoginResponse;
import com.ecomifyapi.auth_service.controller.dtos.dtoUser.UserRequest;
import com.ecomifyapi.auth_service.controller.dtos.dtoUser.UserResponse;
import com.ecomifyapi.auth_service.controller.integrationDTO.UpdatePasswordRequest;
import com.ecomifyapi.auth_service.controller.mapper.UserDtoMapper;
import com.ecomifyapi.auth_service.controller.producer.AuthProducer;
import com.ecomifyapi.auth_service.domain.entities.User;
import com.ecomifyapi.auth_service.infra.authenticate.AuthenticateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Usuário", description = "Endpoints de User")
@RestController
@RequestMapping("ecomifyapi/user")
public class UserController {
    private final CretedUser cretedUser;
    private final UpdateUserRole updateUserRole;
    private final UserDtoMapper userDtoMapper;
    private final AuthProducer authProducer;
    private final UpdatePassword updatePassword;
    private final DeleteUser deleteUser;
    private final AuthenticateUser authenticateUser;

    public UserController(CretedUser cretedUser, UpdateUserRole updateUserRole, UserDtoMapper userDtoMapper, AuthProducer authProducer, UpdatePassword updatePassword, DeleteUser deleteUser, AuthenticateUser authenticateUser) {
        this.cretedUser = cretedUser;
        this.updateUserRole = updateUserRole;
        this.userDtoMapper = userDtoMapper;
        this.authProducer = authProducer;
        this.updatePassword = updatePassword;
        this.deleteUser = deleteUser;
        this.authenticateUser = authenticateUser;
    }

    @Operation(
            summary = "Listar usuários com base nos tokens",
            description = "Testar tokens de determinados usuários retornando seu email"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/teste")
    public String teste(Authentication authentication) {
        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return authentication.getPrincipal().toString();
    }

    @Operation(
            summary = "Login de usuário",
            description = "Fazer login de usuários e gerar token de acesso JWT"
    )
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String token = authenticateUser.authenticate(
                request.email(),
                request.password()
        );

        return new LoginResponse(token);
    }

    @Operation(
            summary = "Cadastrar usuários"
    )
    @PostMapping
    public UserResponse created(@RequestBody UserRequest request) {
        User userBusinessObj = userDtoMapper.toUser(request);
        User user = cretedUser.create(userBusinessObj);

        authProducer.integrar(user);

        return userDtoMapper.toResponse(user);
    }

    @Operation(
            summary = "Atualizar role de usuários",
            description = "Atualizar role de usuários com base no id"
    )
    @PutMapping("update/role/{id}")
    public User updateRole(@RequestBody User user, @Parameter(description = "Id do usuário") @PathVariable Long id) {
        return updateUserRole.update(user, id);
    }

    @Operation(
            summary = "Atualizar password do usuário",
            description = "Atualizar password do usuário com base no id"
    )
    @PutMapping("update/password/{id}")
    public User updatePassword(@RequestBody UpdatePasswordRequest request, @Parameter(description = "Id do usuário") @PathVariable Long id) {
        return updatePassword.update(request.password(), id);
    }

    @Operation(
            summary = "Deletar usuário",
            description = "Deletar usuário com base no id"
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser (@Parameter(description = "Id do usuário") @PathVariable Long id) {
        deleteUser.delete(id);
        return ResponseEntity.noContent().build();
    }
}