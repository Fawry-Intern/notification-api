package com.fawry.notificationapi.model;


import com.fawry.notificationapi.dto.enums.EventType;

public record NotificationRequest(
        NotificationType type,
        EventType eventType,
        Object event
) {
}
