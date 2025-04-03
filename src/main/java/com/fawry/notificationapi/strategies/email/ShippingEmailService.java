package com.fawry.notificationapi.strategies.email;

import com.fawry.kafka.events.order_events.ShippingStatusEvent;
import com.fawry.notificationapi.dto.enums.ShippingStatus;
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
public class ShippingEmailService {

    public void sendEmailForOrderReceiving(ShippingStatusEvent event, JavaMailSender mailSender, SpringTemplateEngine templateEngine, String sourceEmailSender){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(event.getEmail());
            helper.setSubject("Order "+event.getShippingStatus().toString().toLowerCase());
            helper.setFrom(sourceEmailSender, "fawry-intern-8");

            String emailContent = buildEmailContent(event.getEmail(),event.getShippingStatus(), event.getTrackingLink(),event.getConfirmationCode(), event.getOrderId(), templateEngine);
            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);
            log.info("Order email sent to {}", event.getEmail());
        } catch (Exception e) {
            log.error("Failed to send Order email to {}", event.getEmail(), e);
            throw new NotificationException("Failed to send Order email to " + event.getEmail());
        }
    }


    private String buildEmailContent(String email,ShippingStatus shippingStatus, String trackingLink,String confirmationCode,Long orderId, SpringTemplateEngine templateEngine) {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("trackingLink", trackingLink);
        context.setVariable("confirmationCode", confirmationCode);
        context.setVariable("shippingStatus", shippingStatus);
        context.setVariable("orderId",orderId);
        return templateEngine.process("shipping-order", context);
    }

}
