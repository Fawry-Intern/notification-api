package com.fawry.kafka.consumers;

import com.fawry.kafka.events.RegisterEvent;
import com.fawry.kafka.events.enums.EventStatus;
import com.fawry.notificationapi.mapper.NotificationMapper;
import com.fawry.notificationapi.model.FailedRegisterEvent;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.repository.FailedRegisterEventRepository;
import com.fawry.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventConsumer {

    private final NotificationService notificationService;
    private final FailedRegisterEventRepository repository;
    private final NotificationMapper mapper;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 3000, multiplier = 1.5, maxDelay = 15000),
            dltStrategy = DltStrategy.FAIL_ON_ERROR // Required for DLT handling
    )
    @KafkaListener(topics = "user-event", groupId = "notification_id")
    public void handleUserRegister(RegisterEvent event) {
        log.info("Processing registration event: {}", event);

        try {
            NotificationRequest request = mapper.mapFromRegisterEventToNotificationRequest(event);
            notificationService.sendNotification(request);
        } catch (Exception ex) {
            log.error("Failed to process registration event: {}", event, ex);
            throw new RuntimeException("Error processing user registration event", ex);
        }
    }

    @DltHandler
    public void handleDltUserRegister(
            @Payload RegisterEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.error("DLT processing for event: {}", event);

        FailedRegisterEvent failedEvent = FailedRegisterEvent.builder()
                .email(event.getEmail())
                .username(event.getUsername())
                .eventStatus(EventStatus.FAILED)
                .topic(topic)
                .offset(offset)
                .build();

        repository.save(failedEvent);
    }

}

