package com.fawry.notificationapi.events;

public record CouponEvent(
        Long storeId,
        Long userId,
        String couponCode,
        double discount
) {
}
