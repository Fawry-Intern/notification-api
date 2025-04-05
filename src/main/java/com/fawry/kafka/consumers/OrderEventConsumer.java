package com.fawry.kafka.consumers;
import com.fawry.kafka.events.order_events.OrderCancelNotificationEvent;
import com.fawry.kafka.events.user_events.RegisterEvent;
import com.fawry.notificationapi.mapper.NotificationMapper;
import com.fawry.notificationapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {

    private final NotificationService notificationService;
    private final NotificationMapper mapper;

    @KafkaListener(
            topics = "order-events",
            groupId = "order_id",
            topicPartitions = {
                    @TopicPartition(topic = "order-events", partitions = {"3"})
            }
    )    public void handleOrderCancellation(OrderCancelNotificationEvent event) {
        log.info("Processing Order cancellation event: {}", event);

        try {
            var notificationData = mapper.mapFromOrderCanceledEventToNotificationRequest(event);
            notificationService.sendNotification(notificationData);
        } catch (Exception ex) {
            log.error("Failed to process order cancellation: {}", event, ex);
            throw new RuntimeException("Error processing user registration event", ex);
        }
    }

    @DltHandler
    public void handleDltOrderCancellation(
            @Payload RegisterEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.error("DLT processing for event: {}", event);

    }
}
