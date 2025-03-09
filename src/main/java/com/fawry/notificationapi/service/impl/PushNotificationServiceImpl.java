package com.fawry.notificationapi.service.impl;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.repository.NotificationRepository;
import com.fawry.notificationapi.service.PushNotificationService;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

    private final NotificationRepository repository;

    public PushNotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void storeNotification(Notification notification) {
        repository.save(notification);
    }
}
