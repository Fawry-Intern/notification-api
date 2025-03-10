package com.fawry.notificationapi.events;

public record OrderEvent(
        String userEmail,
        String orderId,
        double totalAmount
) {
}
