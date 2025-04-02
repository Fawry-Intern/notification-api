package com.fawry.notificationapi.mapper;

import com.fawry.kafka.events.OrderCancelNotificationEvent;
import com.fawry.kafka.events.RegisterEvent;
import com.fawry.kafka.events.ResetPasswordEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.dto.enums.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {

    public NotificationRequest mapFromRegisterEventToNotificationRequest(RegisterEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.REGISTER)
                .event(event)
                .build();
    }

    public NotificationRequest mapFromResetPasswordEventToNotificationRequest(ResetPasswordEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.RESET_PASSWORD)
                .event(event)
                .build();
    }

    public NotificationRequest mapFromOrderCanceledEventToNotificationRequest(OrderCancelNotificationEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.ORDER_CANCELLATION)
                .event(event)
                .build();
    }
}
