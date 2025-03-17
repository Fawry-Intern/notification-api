package com.fawry.notificationapi.strategies.email;

import com.fawry.kafka.events.RegisterEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class Register {


    public void sendEmailForRegistration(RegisterEvent event, JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
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
