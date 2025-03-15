package com.fawry.notificationapi.factory;

import com.fawry.notificationapi.exceptions.UnsupportedNotificationTypeException;
import com.fawry.notificationapi.handler.EmailNotificationHandler;
import com.fawry.notificationapi.handler.NotificationHandler;
import com.fawry.notificationapi.handler.PushNotificationHandler;
import com.fawry.notificationapi.handler.SMSNotificationHandler;
import com.fawry.notificationapi.model.NotificationType;

import org.springframework.stereotype.Component;

@Component
public class NotificationHandlerFactory {

    private final EmailNotificationHandler emailNotificationHandler;
    private final SMSNotificationHandler smsNotificationHandler;
    private final PushNotificationHandler pushNotificationService;

    public NotificationHandlerFactory(EmailNotificationHandler emailNotificationHandler, SMSNotificationHandler smsNotificationHandler, PushNotificationHandler pushNotificationService) {
        this.emailNotificationHandler = emailNotificationHandler;
        this.smsNotificationHandler = smsNotificationHandler;
        this.pushNotificationService = pushNotificationService;
    }


    public NotificationHandler getNotificationHandler(NotificationType type) {
        switch (type) {
            case EMAIL -> {
                return emailNotificationHandler;
            }
            case SMS -> {
                return smsNotificationHandler;
            }
            case PUSH -> {
                return pushNotificationService;
            }
            default -> {
                throw new UnsupportedNotificationTypeException(String.format("Unsupported notification type {} ", type));
            }
        }
    }
}
