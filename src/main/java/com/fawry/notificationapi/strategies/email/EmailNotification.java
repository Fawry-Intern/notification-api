package com.fawry.notificationapi.strategies.email;

import com.fawry.kafka.events.order_events.OrderCancelNotificationEvent;
import com.fawry.kafka.events.order_events.ShippingStatusEvent;
import com.fawry.kafka.events.user_events.RegisterEvent;
import com.fawry.kafka.events.user_events.ResetPasswordEvent;
import com.fawry.notificationapi.exceptions.UnsupportedNotificationTypeException;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
@RequiredArgsConstructor
public class EmailNotification implements NotificationStrategy {

    private final Logger log = LoggerFactory.getLogger(EmailNotification.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private final RegisterService registerService;
    private final ResetPasswordService resetPasswordService;
    private final OrderCancellationService cancellationService;;
    private final ShippingEmailService shippingEmailService;

    @Value("${source.sender.email}")
    private String SOURCE_SENDER_MAIL;


    @Override
    public void sendNotification(NotificationRequest request) {

        switch (request.eventType()) {
            case REGISTER -> {
                sendEmailForRegistration((RegisterEvent) request.event());
            }
            case RESET_PASSWORD -> {
                sendEmailForResetPassword((ResetPasswordEvent) request.event());
            }
            case ORDER_CANCELLATION -> {
                sendEmailForOrderCancellation((OrderCancelNotificationEvent) request.event());
            }
            case Receiving_Order -> {
                sendEmailForOrderReceiving((ShippingStatusEvent) request.event());
            }

            default -> {
                throw new UnsupportedNotificationTypeException("event type not supported");
            }
        }

    }

    private void sendEmailForRegistration(RegisterEvent event) {
        registerService.sendEmailForRegistration(
                event,
                mailSender,
                templateEngine,
                SOURCE_SENDER_MAIL
        );
    }

    private void sendEmailForResetPassword(ResetPasswordEvent event) {
        resetPasswordService.sendEmailForResetPassword(event, mailSender, templateEngine, SOURCE_SENDER_MAIL);
    }

    private void sendEmailForOrderCancellation(OrderCancelNotificationEvent event) {
        cancellationService.sendEmailForOrderCancellation(event, mailSender, templateEngine, SOURCE_SENDER_MAIL);
    }

    private void sendEmailForOrderReceiving(ShippingStatusEvent event) {
        shippingEmailService.sendEmailForOrderReceiving(event, mailSender, templateEngine, SOURCE_SENDER_MAIL);
    }

}
