package com.ecomifyapi.auth_service.controller.integrationDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para atualização de senha")
public record UpdatePasswordRequest(@Schema(description = "Nova senha do usuário", example = "novaSenha123") String password) {
}
