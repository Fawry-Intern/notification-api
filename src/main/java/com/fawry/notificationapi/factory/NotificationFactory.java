package com.fawry.notificationapi.factory;

import com.fawry.notificationapi.exceptions.UnsupportedNotificationTypeException;
import com.fawry.notificationapi.strategies.email.EmailNotification;
import com.fawry.notificationapi.strategies.push.PushNotification;
import com.fawry.notificationapi.strategies.real_time.WebSocketService;
import com.fawry.notificationapi.strategies.sms.SMSNotification;
import com.fawry.notificationapi.strategy.NotificationStrategy;
import com.fawry.notificationapi.dto.enums.NotificationType;

import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    private final EmailNotification emailNotification;
    private final SMSNotification smsNotification;
    private final PushNotification pushNotification;
    private final WebSocketService webSocketService;

    public NotificationFactory(EmailNotification emailNotification, SMSNotification smsNotification, PushNotification pushNotification, WebSocketService webSocketService) {
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
        this.pushNotification = pushNotification;
        this.webSocketService = webSocketService;
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
            case REAL_TIME ->{
                return webSocketService;
            }
            default -> {
                throw new UnsupportedNotificationTypeException(String.format("Unsupported notification type {} ", type));
            }
        }
    }


}
