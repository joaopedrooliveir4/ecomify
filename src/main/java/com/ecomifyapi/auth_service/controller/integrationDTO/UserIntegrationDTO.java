package com.ecomifyapi.auth_service.controller.integrationDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para integração com sistemas externos")
public record UserIntegrationDTO(
        @Schema(description = "Email do usuário", example = "exemplo@email.com") String email,
        @Schema(description = "Nome do usuário", example = "João") String nome
) {}