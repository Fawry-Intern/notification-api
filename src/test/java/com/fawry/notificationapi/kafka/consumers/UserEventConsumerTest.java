package com.fawry.notificationapi.kafka.consumers;

import com.fawry.kafka.consumers.UserEventConsumer;
import com.fawry.kafka.events.RegisterEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.dto.enums.NotificationType;
import com.fawry.notificationapi.mapper.NotificationMapper;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.repository.FailedRegisterEventRepository;
import com.fawry.notificationapi.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class UserEventConsumerTest {

    @Mock
    private NotificationServiceImpl notificationService;
    @Mock
    private FailedRegisterEventRepository repository;
    @Mock
    private NotificationMapper mapper;
    @Mock
    private Logger log;

    @InjectMocks
    private UserEventConsumer userEventConsumer;

    @Test
    public void givenValidRegisterEvent_whenHandleRegisterEvent_thenSendNotificationSuccessfully() {

        // Given
        var event = new RegisterEvent("muhammadhussein2312@gmail.com", "muhammad hussein");
        var notificationRequest = NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.REGISTER)
                .event(event)
                .build();

        Mockito.when(mapper.mapFromRegisterEventToNotificationRequest(event)).thenReturn(notificationRequest);
        Mockito.doNothing().when(notificationService).sendNotification(notificationRequest);

        // When
        userEventConsumer.handleUserRegister(event);

        // Then
        verify(log).info("Processing registration event: {}", event);
        verify(notificationService, Mockito.times(1)).sendNotification(notificationRequest);
        verifyNoMoreInteractions(notificationService,mapper);
    }

    @Test
    public void givenRegisterEventWithMapperFailure_whenHandleUserRegister_thenThrowException() {
        // Given
        var event = new RegisterEvent("muhammadhussein2312@gmail.com", "muhammad hussein");
        Mockito.when(mapper.mapFromRegisterEventToNotificationRequest(event)).thenThrow(new RuntimeException("Mapping failed"));

        // When
        var exception = assertThrows(RuntimeException.class, () -> userEventConsumer.handleUserRegister(event));

        // Then
        assertEquals("Error processing user registration event", exception.getMessage());
        verify(log).info("Processing registration event: {}", event);
        verify(log).error(eq("Failed to process registration event: {}"), eq(event), any(Exception.class));

    }

}
