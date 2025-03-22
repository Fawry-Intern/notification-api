package com.fawry.kafka.consumers;

import com.fawry.kafka.events.ResetPasswordEvent;
import com.fawry.notificationapi.mapper.NotificationMapper;
import com.fawry.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordEventConsumer {

    private final NotificationService notificationService;
    private final NotificationMapper mapper;

    @KafkaListener(topics = "reset-password-events", groupId = "notification_id")
    public void listenResetPasswordEvent(ResetPasswordEvent resetPasswordEvent){

        System.out.println(resetPasswordEvent);
        var notificationRequest = mapper.mapFromResetPasswordEventToNotificationRequest(resetPasswordEvent);

        notificationService.sendNotification(notificationRequest);
    }
}
