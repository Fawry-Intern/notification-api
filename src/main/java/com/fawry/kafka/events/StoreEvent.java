package com.fawry.kafka.events;

public record StoreEvent(
        Long storeId,
        Long userId,
        String storeName
) {

}
