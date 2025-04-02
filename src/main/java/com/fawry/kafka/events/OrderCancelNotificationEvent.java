package com.fawry.kafka.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.time.Instant;


public record OrderCancelNotificationEvent(
        Long orderId,
        String reason,
        String customerEmail,
        Instant cancellationDate
) implements Serializable {

    @JsonCreator
    public OrderCancelNotificationEvent(@JsonProperty("orderId") Long orderId,
                                        @JsonProperty("reason") String reason,
                                        @JsonProperty("customerEmail") String customerEmail,
                                        @JsonProperty("cancellationDate") Instant cancellationDate) {
        this.orderId = orderId;
        this.reason = reason;
        this.customerEmail = customerEmail;
        this.cancellationDate = cancellationDate;
    }

    public static OrderCancelNotificationEvent newInstance(Long orderId, String reason, String customerEmail, Instant cancellationDate) {
        return new OrderCancelNotificationEvent(orderId, reason, customerEmail, cancellationDate);
    }
}
