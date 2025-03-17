package com.fawry.notificationapi.strategies.email;

import com.fawry.kafka.events.RegisterEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.model.NotificationRequest;

import com.fawry.notificationapi.strategy.NotificationStrategy;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class EmailNotification implements NotificationStrategy {

    private final Logger log = LoggerFactory.getLogger(EmailNotification.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private final Register register;

    @Value("${source.sender.email}")
    private String SOURCE_SENDER_MAIL;


    @Override
    public void sendNotification(NotificationRequest request) {

        if (Objects.requireNonNull(request.eventType()) == EventType.REGISTER) {

            sendEmailForRegistration((RegisterEvent) request.event());
        } else {
            throw new IllegalArgumentException("EventType not supported");
        }
    }

    private void sendEmailForRegistration(RegisterEvent event) {
        register.sendEmailForRegistration(event, mailSender, templateEngine);
    }
}
