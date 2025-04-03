package com.fawry.notificationapi.strategies.email;

import com.fawry.kafka.events.order_events.OrderCancelNotificationEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;


@Service
@Slf4j
public class OrderCancellationService {

    public void sendEmailForOrderCancellation(OrderCancelNotificationEvent event, JavaMailSender mailSender, SpringTemplateEngine templateEngine, String sourceEmailSender) {
        try {
            // Prepare the email content using Thymeleaf template
            Context context = new Context();
            context.setVariable("orderId", event.getOrderId());
            context.setVariable("customerName", "Muhammad hussein");
            context.setVariable("reason", event.getReason());
            context.setVariable("cancellationDate", event.getCancellationDate());
            String emailContent = templateEngine.process("order-cancellation-email", context);

            // Create and configure the email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(sourceEmailSender, "fawry");
            helper.setTo(event.getEmail());
            helper.setSubject("Order Cancellation Notification - Order #" + event.getOrderId());
            helper.setText(emailContent, true); // true = HTML content

            // Send the email
            mailSender.send(mimeMessage);
            log.info("Cancellation email sent successfully to {} for order {}", event.getEmail(), event.getOrderId());

        } catch (MessagingException e) {
            log.error("Failed to send cancellation email for order {}: {}", event.getOrderId(), e.getMessage());
            throw new RuntimeException("Error sending cancellation email", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
