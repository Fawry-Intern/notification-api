package com.fawry.kafka.consumers;

import com.fawry.kafka.events.ResetPasswordEvent;
import com.fawry.notificationapi.mapper.NotificationMapper;
import com.fawry.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordEventConsumer {

    private final NotificationService notificationService;
    private final NotificationMapper mapper;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 3000, multiplier = 1.5, maxDelay = 15000),
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(topics = "reset-password-events", groupId = "notification_id")
    public void listenResetPasswordEvent(ResetPasswordEvent resetPasswordEvent){

        System.out.println(resetPasswordEvent);
        var notificationRequest = mapper.mapFromResetPasswordEventToNotificationRequest(resetPasswordEvent);

        notificationService.sendNotification(notificationRequest);
    }
}
