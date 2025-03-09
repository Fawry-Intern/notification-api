package com.fawry.notificationapi.handler;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.mapper.MapperService;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.PushNotificationService;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationHandler implements NotificationHandler {
    private final PushNotificationService service;
    private final MapperService mapper;

    public PushNotificationHandler(PushNotificationService service, MapperService mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public void sendNotification(NotificationRequest request) {


        /*
        // push notification to user
           websocket
         */

        // store notification to database

        var notification = createNewNotification(request);
        service.storeNotification(notification);
    }

    private Notification createNewNotification(NotificationRequest request) {
        return mapper.convertToNotification(request);
    }


}
