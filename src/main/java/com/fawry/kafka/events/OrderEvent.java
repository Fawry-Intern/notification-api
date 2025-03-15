package com.fawry.kafka.events;

public record OrderEvent(
        String userEmail,
        String orderId,
        double totalAmount
) {
}
