package com.fawry.notificationapi.mapper;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.model.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public Notification convertToNotification(NotificationRequest request) {

        return Notification.builder()
                .notificationId(null)
                .build();
    }
}
