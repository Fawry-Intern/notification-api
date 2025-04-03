package com.fawry.kafka.events.order_events;

import com.fawry.kafka.events.BaseEvent;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class OrderEvent extends BaseEvent {
    private final Long orderId;

    public OrderEvent(String email, Long orderId) {
        super(email);
        this.orderId = orderId;
    }
}
