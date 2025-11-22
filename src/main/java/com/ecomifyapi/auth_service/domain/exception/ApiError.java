package com.ecomifyapi.auth_service.domain.exception;

import java.util.Map;

public class ApiError {
    private Integer status;
    private Map<String,String> message;

    public ApiError(Integer status, Map<String, String> message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}
