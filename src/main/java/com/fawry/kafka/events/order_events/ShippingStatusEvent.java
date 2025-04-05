package com.fawry.kafka.events.order_events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fawry.notificationapi.dto.enums.ShippingStatus;

public class ShippingStatusEvent extends OrderEvent {

    private final String trackingLink;
    private final String confirmationCode;
    private final ShippingStatus shippingStatus;
    public ShippingStatusEvent(@JsonProperty("email") String email,
                               @JsonProperty("trackingLink") String trackingLink,
                               @JsonProperty("confirmationCode") String confirmationCode,
                               @JsonProperty("orderId") Long orderId,
                               @JsonProperty("shippingStatus") ShippingStatus shippingStatus) {
        super(email,orderId);
        this.trackingLink = trackingLink;
        this.confirmationCode = confirmationCode;
        this.shippingStatus = shippingStatus;
    }


    public String getTrackingLink() {
        return trackingLink;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }
}
