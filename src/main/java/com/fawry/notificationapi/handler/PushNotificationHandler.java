package com.fawry.notificationapi.handler;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.mapper.MapperService;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.PushNotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationHandler implements NotificationHandler {
    private final PushNotificationService service;
    private final SimpMessagingTemplate messagingTemplate;
    private final MapperService mapper;

    public PushNotificationHandler(PushNotificationService service, SimpMessagingTemplate messagingTemplate, MapperService mapper) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
        this.mapper = mapper;
    }

    @Override
    public void sendNotification(NotificationRequest request) {


        // Send real-time notification via WebSocket
        sendRealTieNotification(request);

        // Store notification int the database

        var notification = createNewNotification(request);
        service.storeNotification(notification);
    }

    private Notification createNewNotification(NotificationRequest request) {
        return mapper.convertToNotification(request);
    }

    private void sendRealTieNotification(NotificationRequest request) {
        System.out.println("Sending real-time notification: " + request);
        messagingTemplate.convertAndSend("/topic/notifications", request);
    }


}
