package com.jota_nunes_back_end.jotanunes.consumers;

import com.jota_nunes_back_end.jotanunes.dtos.EmailDto;
import com.jota_nunes_back_end.jotanunes.models.Email;
import com.jota_nunes_back_end.jotanunes.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendEmail(email);
        System.out.println("Email Status: " + email.getStatusEmail().toString());
    }
}
