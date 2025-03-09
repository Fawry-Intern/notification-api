package com.fawry.notificationapi.service.impl;

import com.fawry.notificationapi.factory.NotificationHandlerFactory;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationHandlerFactory notificationHandlerFactory;

    public NotificationServiceImpl(NotificationHandlerFactory notificationHandlerFactory) {
        this.notificationHandlerFactory = notificationHandlerFactory;
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        var handler = notificationHandlerFactory.getNotificationHandler(request.type());
        handler.sendNotification(request);
    }
}
