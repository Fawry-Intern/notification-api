package com.fawry.notificationapi.strategy;

import com.fawry.notificationapi.model.NotificationRequest;

public interface NotificationStrategy {
    void sendNotification(NotificationRequest request);
}
