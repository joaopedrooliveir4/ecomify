package com.ecomifyapi.email_service.service;

import com.ecomifyapi.email_service.integration.UserIntegrationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final SendGridEmailService emailService;

    public EmailConsumer(SendGridEmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email.queue")
    public void receberMensagem(UserIntegrationDTO dto) {
        System.out.println("Email recebido: " + dto.email());
        System.out.println("Nome: " + dto.nome());

        enviarEmailDeBoasVindas(dto.email(), dto.nome());
    }

    private void enviarEmailDeBoasVindas(String email, String name) {

        String subject = "Bem-vindo à Ecomify!";
        String body = """
                Olá %s,

                Seu cadastro foi realizado com sucesso.
                Ficamos muito felizes de ter você se juntando à família Ecomify 🚀
                
                Atenciosamente,
                Equipe Ecomify
                """.formatted(name);

        emailService.enviarEmail(email, subject, body);
    }
}