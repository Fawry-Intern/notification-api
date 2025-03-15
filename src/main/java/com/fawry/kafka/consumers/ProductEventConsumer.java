package com.fawry.kafka.consumers;

import com.fawry.kafka.events.ProductEvent;
import com.fawry.notificationapi.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProductEventConsumer {

    private final NotificationService notificationService;
    private final Logger log = LoggerFactory.getLogger(ProductEventConsumer.class);

    public ProductEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "product-event")
    public void consumeProductEvent(ProductEvent event) {

        log.info("Received event: {}", event);
    }
}
