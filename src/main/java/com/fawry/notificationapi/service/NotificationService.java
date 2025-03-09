package com.fawry.notificationapi.service;

import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.model.NotificationType;

public interface NotificationService {
    void sendNotification(NotificationRequest request);
}
