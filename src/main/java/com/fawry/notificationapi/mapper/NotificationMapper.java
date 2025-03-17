package com.fawry.notificationapi.mapper;


import com.fawry.kafka.events.RegisterEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.model.NotificationType;
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
}
