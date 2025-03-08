package com.fawry.notificationapi.handler;

import com.fawry.notificationapi.model.NotificationRequest;

public interface NotificationHandler {
    void sendNotification(NotificationRequest request);
}
