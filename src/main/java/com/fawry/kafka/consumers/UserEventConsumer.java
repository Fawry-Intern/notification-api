package com.fawry.kafka.consumers;

import com.fawry.kafka.events.OrderEvent;
import com.fawry.kafka.events.RegisterEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.model.NotificationType;
import com.fawry.notificationapi.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    private final NotificationService notificationService;
    private final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);

    public UserEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "user-event")
    public void consumeRegisterEvent(RegisterEvent event) {
        log.info("Send email to notificationEmail to send email to {}", event);

        // This will handle using the EmailNotification Strategy
        // EventType is RegisterEvent
        // this.event

        NotificationRequest request = new NotificationRequest(NotificationType.EMAIL, EventType.REGISTER, event);

        notificationService.sendNotification(request);
    }

}
