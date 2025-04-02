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
public class RegisterService {


    public void sendEmailForRegistration(RegisterEvent event, JavaMailSender mailSender, SpringTemplateEngine templateEngine, String sourceEmailSender) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(event.getEmail());
            helper.setSubject("Welcome to Our Platform - Verify Your Email");
            helper.setFrom(sourceEmailSender, "Fawry");

            helper.addInline("logo",
                    new ClassPathResource("static/images/logo.png"));

            Context context = new Context();


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
