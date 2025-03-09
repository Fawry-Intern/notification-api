package com.fawry.notificationapi.service;

import com.fawry.notificationapi.model.NotificationRequest;

public interface NotificationService {
    void sendNotification(NotificationRequest request);
}
