package com.fawry.notificationapi.service.impl;

import com.fawry.notificationapi.factory.NotificationFactory;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationHandlerFactory;

    @Override
    public void sendNotification(NotificationRequest request) {
        var handler = notificationHandlerFactory.getNotificationStrategy(request.notificationType());
        handler.sendNotification(request);
    }

    @Override
    public void resendNotification(NotificationRequest request) {
        var handler = notificationHandlerFactory.getNotificationStrategy(request.notificationType());
        handler.sendNotification(request);
    }
}
