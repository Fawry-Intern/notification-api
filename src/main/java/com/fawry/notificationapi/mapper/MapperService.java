package com.fawry.notificationapi.mapper;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.model.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public Notification convertToNotification(NotificationRequest request) {

        Notification notification = new Notification();
        notification.setNotificationType(request.type());
        notification.setMessage(request.message());
        notification.setUserId(324L);
        notification.setRead(false);
        return notification;
    }
}
