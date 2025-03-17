package com.fawry.notificationapi.strategies;

import com.fawry.kafka.events.RegisterEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.model.NotificationRequest;

import com.fawry.notificationapi.strategy.NotificationStrategy;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;


@Service
public class EmailNotification implements NotificationStrategy {

    private final Logger log = LoggerFactory.getLogger(EmailNotification.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${source.sender.email}")
    private String SOURCE_SENDER_MAIL;

    public EmailNotification(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        switch (request.eventType()) {
            case REGISTER -> {
                sendEmailForRegistration((RegisterEvent) request.event());
            }
            default -> {
                throw new IllegalArgumentException("EventType not supported");
            }
        }

    }

    private void sendEmailForRegistration(RegisterEvent event) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Set email details
            helper.setTo(event.getEmail());
            helper.setSubject("Welcome to Our Platform - Verify Your Email");
            helper.setFrom("noreply@company.com", "Fawry");

            // Add inline logo (make sure path is correct)
            helper.addInline("logo",
                    new ClassPathResource("static/images/logo.png"));

            // Create Thymeleaf context
            Context context = new Context();
            context.setVariable("username", event.getUsername());
            context.setVariable("verificationLink",
                    "https://yourdomain.com/verify-email?token=" + "sdfsd323");

            // Process template
            String htmlContent = templateEngine.process("email-register", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Registration email sent to: {}", event.getEmail());
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send registration email to {}: {}", event.getEmail(), e.getMessage());
            throw new RuntimeException("Failed to send registration email", e);
        }
    }
}
