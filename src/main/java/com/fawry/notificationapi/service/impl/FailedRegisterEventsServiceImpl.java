package com.fawry.notificationapi.service.impl;

import com.fawry.kafka.events.RegisterEvent;
import com.fawry.kafka.events.enums.EventStatus;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.entities.FailedRegisterEvent;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.dto.enums.NotificationType;
import com.fawry.notificationapi.repository.FailedRegisterEventRepository;
import com.fawry.notificationapi.service.FailedRegisterEventService;
import com.fawry.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FailedRegisterEventsServiceImpl implements FailedRegisterEventService {

    private final FailedRegisterEventRepository repository;
    private final NotificationService notificationService;

    @Override
    @Scheduled(cron = "0 0 3 * * *")
    public void retryFailedRegisterEvents() {

        List<FailedRegisterEvent> failedRegisterEvents = repository.findFailedRegisterEventByEventStatusIn(
                List.of(EventStatus.FAILED, EventStatus.RETRYING)
        );

        for (var fre : failedRegisterEvents) {

            try {
                RegisterEvent registerEvent = new RegisterEvent(fre.getEmail());

                var notificationRequest = NotificationRequest.builder()
                        .notificationType(NotificationType.EMAIL)
                        .eventType(EventType.REGISTER)
                        .event(registerEvent)
                        .build();
                notificationService.resendNotification(notificationRequest);

                fre.setEventStatus(EventStatus.SUCCEEDED);

            }catch (Exception ex) {
                fre.setRetryCount(fre.getRetryCount() + 1);
            }

            repository.save(fre);
        }

    }
}
