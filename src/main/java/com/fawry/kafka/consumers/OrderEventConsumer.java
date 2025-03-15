package com.fawry.kafka.consumers;

import com.fawry.kafka.events.OrderEvent;
import com.fawry.notificationapi.service.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private final NotificationService notificationService;


    public OrderEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void consumeOrderEvent(OrderEvent event) {

        /*
        // create notification request and send to notification service to handl it
         */
    }
}
