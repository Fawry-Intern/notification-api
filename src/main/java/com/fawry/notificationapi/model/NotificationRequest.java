package com.fawry.notificationapi.model;


public record NotificationRequest(
        NotificationType type,
        String email,
        String message,
        Object event
) {
}
