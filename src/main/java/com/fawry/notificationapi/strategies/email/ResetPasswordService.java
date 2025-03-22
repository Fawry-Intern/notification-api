package com.fawry.notificationapi.strategies.email;

import com.fawry.kafka.events.ResetPasswordEvent;
import com.fawry.notificationapi.exceptions.NotificationException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
public class ResetPasswordService {

    public void sendEmailForResetPassword(ResetPasswordEvent event, JavaMailSender mailSender, SpringTemplateEngine templateEngine, String sourceEmailSender){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(event.getEmail());
            helper.setSubject("Reset Your Password");
            helper.setFrom(sourceEmailSender, "fawry-intern-8");

            String emailContent = buildEmailContent(event.getUsername(), event.getLinkToken(), templateEngine);
            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);
            log.info("Reset password email sent to {}", event.getEmail());
        } catch (Exception e) {
            log.error("Failed to send reset password email to {}", event.getEmail(), e);
            throw new NotificationException("Failed to send reset password email to " + event.getEmail());
        }
    }

    private String buildEmailContent(String username, String linkToken, SpringTemplateEngine templateEngine) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("linkToken", linkToken);
        return templateEngine.process("reset-password", context);
    }
}
