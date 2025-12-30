package com.ecomifyapi.auth_service.controller.dtos.dtoLogin;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do backend após login")
public record LoginResponse(
        @Schema(description = "Token JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", accessMode = Schema.AccessMode.READ_ONLY)
        String token
) {}
