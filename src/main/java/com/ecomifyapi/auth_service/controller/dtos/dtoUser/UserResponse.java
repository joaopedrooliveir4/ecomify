package com.ecomifyapi.auth_service.controller.dtos.dtoUser;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do backend sobre o usuário")
public class UserResponse {
    @Schema(description = "Mensagem de retorno", example = "Usuário criado com sucesso")
    private String message;

    public UserResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
