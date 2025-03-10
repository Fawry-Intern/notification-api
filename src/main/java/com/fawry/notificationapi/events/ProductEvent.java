package com.fawry.notificationapi.events;

public record ProductEvent(
        String userEmail,
        String productId,
        String productName
) {
}
