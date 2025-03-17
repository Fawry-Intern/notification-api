package com.fawry.kafka.consumers;

import com.fawry.kafka.events.OrderEvent;
import com.fawry.notificationapi.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class BankEventConsumer {

    private final NotificationService notificationService;

    public BankEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void consumeOrderEvent(OrderEvent event) {
    }
}
