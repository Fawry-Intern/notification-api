package com.fawry.notificationapi.factory;

import com.fawry.notificationapi.exceptions.UnsupportedNotificationTypeException;
import com.fawry.notificationapi.strategies.email.EmailNotification;
import com.fawry.notificationapi.strategies.push.PushNotification;
import com.fawry.notificationapi.strategies.sms.SMSNotification;
import com.fawry.notificationapi.strategy.NotificationStrategy;
import com.fawry.notificationapi.model.NotificationType;

import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    private final EmailNotification emailNotification;
    private final SMSNotification smsNotification;
    private final PushNotification pushNotification;

    public NotificationFactory(EmailNotification emailNotification, SMSNotification smsNotification, PushNotification pushNotification) {
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
        this.pushNotification = pushNotification;
    }


    public NotificationStrategy getNotificationStrategy(NotificationType type) {
        switch (type) {
            case EMAIL -> {
                return emailNotification;
            }
            case SMS -> {
                return smsNotification;
            }
            case PUSH -> {
                return pushNotification;
            }
            default -> {
                throw new UnsupportedNotificationTypeException(String.format("Unsupported notification type {} ", type));
            }
        }
    }
}
