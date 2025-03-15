package com.fawry.notificationapi.strategies;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.mapper.MapperService;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.PushNotificationService;
import com.fawry.notificationapi.strategy.NotificationStrategy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PushNotification implements NotificationStrategy {
    private final PushNotificationService service;
    private final SimpMessagingTemplate messagingTemplate;
    private final MapperService mapper;

    public PushNotification(PushNotificationService service, SimpMessagingTemplate messagingTemplate, MapperService mapper) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
        this.mapper = mapper;
    }

    @Override
    public void sendNotification(NotificationRequest request) {

    }

}
