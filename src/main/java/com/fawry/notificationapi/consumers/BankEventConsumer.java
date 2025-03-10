package com.fawry.notificationapi.consumers;

import com.fawry.notificationapi.events.OrderEvent;
import com.fawry.notificationapi.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class BankEventConsumer {

    private final NotificationService notificationService;

    public BankEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void consumeOrderEvent(OrderEvent event) {

        /*
        // create notification request and send to notification service to handl it
         */

    }
}
