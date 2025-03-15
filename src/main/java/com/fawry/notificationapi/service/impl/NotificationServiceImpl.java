package com.fawry.notificationapi.service.impl;

import com.fawry.notificationapi.factory.NotificationFactory;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationHandlerFactory;

    public NotificationServiceImpl(NotificationFactory notificationHandlerFactory) {
        this.notificationHandlerFactory = notificationHandlerFactory;
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        var handler = notificationHandlerFactory.getNotificationStrategy(request.type());
        handler.sendNotification(request);
    }
}
