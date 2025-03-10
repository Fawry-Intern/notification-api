package com.fawry.notificationapi.events;

public record StoreEvent(
        Long storeId,
        Long userId,
        String storeName
) {

}
