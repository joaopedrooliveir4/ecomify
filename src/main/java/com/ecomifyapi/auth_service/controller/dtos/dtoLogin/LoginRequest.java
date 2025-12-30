package com.ecomifyapi.auth_service.controller.dtos.dtoLogin;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados enviados pelo cliente para login")
public record LoginRequest(
        @Schema(description = "Email do usuário", example = "joao@email.com") String email,
        @Schema(description = "Senha do usuário", example = "1234567890") String password
) {
}
