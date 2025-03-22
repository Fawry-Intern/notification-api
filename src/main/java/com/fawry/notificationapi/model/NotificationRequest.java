package com.fawry.notificationapi.model;


import com.fawry.notificationapi.dto.enums.NotificationType;
import com.fawry.notificationapi.dto.enums.EventType;
import lombok.Builder;

@Builder
public record NotificationRequest(
        NotificationType notificationType,
        EventType eventType,
        Object event
) {
}
