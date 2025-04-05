package com.fawry.kafka.events.order_events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class OrderCancelNotificationEvent extends OrderEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String reason;
    private final Instant cancellationDate;

    @JsonCreator
    public OrderCancelNotificationEvent(
            @JsonProperty("orderId") Long orderId,
            @JsonProperty("reason") String reason,
            @JsonProperty("customerEmail") String customerEmail,
            @JsonProperty("cancellationDate") Instant cancellationDate
    ) {
        super(customerEmail, orderId);
        this.reason = reason;
        this.cancellationDate = cancellationDate;
    }

    public static OrderCancelNotificationEvent newInstance(Long orderId, String reason, String customerEmail, Instant cancellationDate) {
        return new OrderCancelNotificationEvent(orderId, reason, customerEmail, cancellationDate);
    }
}
