package com.fawry.notificationapi.service;

import com.fawry.notificationapi.entities.Notification;

public interface PushNotificationService {
    void storeNotification(Notification notification);
}
