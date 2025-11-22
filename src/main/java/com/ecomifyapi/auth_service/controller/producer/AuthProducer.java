package com.ecomifyapi.auth_service.controller.producer;

import com.ecomifyapi.auth_service.config.rabbit.RabbitConfig;
import com.ecomifyapi.auth_service.controller.integrationDTO.UserIntegrationDTO;
import com.ecomifyapi.auth_service.domain.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthProducer {
    private final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthProducer.class);

    public AuthProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void integrar (User user) {
        var dto = new UserIntegrationDTO(user.getEmail(), user.getName());
        LOGGER.info("Enviado para a fila: {}", dto);

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY_REGISTRATION,
                dto
        );
    }
}