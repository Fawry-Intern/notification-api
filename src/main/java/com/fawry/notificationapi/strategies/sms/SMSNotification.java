package com.fawry.notificationapi.strategies.sms;

import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.strategy.NotificationStrategy;
import org.springframework.stereotype.Service;

@Service
public class SMSNotification implements NotificationStrategy {

    @Override
    public void sendNotification(NotificationRequest request) {
    }
}
