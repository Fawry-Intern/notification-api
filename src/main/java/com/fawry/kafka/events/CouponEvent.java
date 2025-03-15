package com.fawry.kafka.events;

public record CouponEvent(
        Long storeId,
        Long userId,
        String couponCode,
        double discount
) {
}
