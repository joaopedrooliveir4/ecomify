package com.ecomifyapi.auth_service.controller.dtos.dtoUser;

import com.ecomifyapi.auth_service.domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados enviados pelo cliente para criar/atualizar um usuário")
public class UserRequest {
    @Schema(description = "Nome do usuário", example = "João")
    private String name;
    @Schema(description = "Email do usuário", example = "joao@email.com")
    private final String email;
    @Schema(description = "Senha do usuário", example = "1234567890")
    private String password;
    @Schema(description = "Perfil do usuário (não enviado pelo cliente)", accessMode = Schema.AccessMode.READ_ONLY)
    private Role role;
    @Schema(description = "Data de criação (não enviada pelo cliente)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    public UserRequest(String name, String email, String password, Role role, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
