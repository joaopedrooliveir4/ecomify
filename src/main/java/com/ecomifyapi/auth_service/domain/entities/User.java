package com.ecomifyapi.auth_service.domain.entities;

import com.ecomifyapi.auth_service.domain.enums.Role;
import com.ecomifyapi.auth_service.domain.exception.EmailArgumentException;
import com.ecomifyapi.auth_service.domain.exception.NameArgumentException;
import com.ecomifyapi.auth_service.domain.exception.PasswordArgumentException;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "entidade de usuário")
public class User {

    @Schema(description = "Nome do usuário", example = "João")
    private String name;
    @Schema(description = "Email do usuário", example = "exemplo@email.com")
    private String email;
    @Schema(description = "Password do usuário", example = "1234567890")
    private String password;
    @Schema(description = "Perfil do usuário", example = "USER", accessMode = Schema.AccessMode.READ_ONLY)
    private Role role;
    @Schema(description = "Data de criação do usuário", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    public User(String name, String email, String password) {
        if (name == null || name.isBlank()) {
            throw new NameArgumentException("Name não pode ser vazio");
        }
        if (email == null || !email.contains("@")) {
            throw new EmailArgumentException("Email inválido");
        }
        if (password == null || password.length() < 6) {
            throw new PasswordArgumentException("Senha deve ter pelo menos 6 caracteres");
        }

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.USER; // sempre começa como USER
        this.createdAt = LocalDateTime.now();
    }

    public void promoteToAdmin() {
        this.role = Role.ADMIN;
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
