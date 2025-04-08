package com.fawry.kafka.events.order_events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fawry.notificationapi.dto.CustomerDetails;
import com.fawry.notificationapi.dto.DeliveryPersonDetails;
import com.fawry.notificationapi.dto.enums.ShippingStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
public class ShippingDetailsEvent  {

    private final CustomerDetails customerDetails;
    private final Long shipmentId;
    private final DeliveryPersonDetails deliveryPerson;
    private final LocalDateTime deliveredAt;
    private  final LocalDateTime expectedDeliveryDate;
    private final Long orderId;
    private  final ShippingStatus shippingStatus;
    public ShippingDetailsEvent(@JsonProperty("orderId") Long orderId,
                                @JsonProperty("status") ShippingStatus shippingStatus,
                                @JsonProperty("customerDetails") CustomerDetails customerDetails,
                                @JsonProperty("shipmentId") Long shipmentId,
                                @JsonProperty("deliveryPersonDetails") DeliveryPersonDetails deliveryPersonDetails,
                                @JsonProperty("deliveredAt") LocalDateTime deliveredAt,
                                @JsonProperty("expectedDeliveryDate") LocalDateTime expectedDeliveryDate) {
        this.orderId=orderId;
        this.shippingStatus=shippingStatus;
        this.customerDetails = customerDetails;
        this.shipmentId = shipmentId;
        this.deliveryPerson = deliveryPersonDetails;
        this.deliveredAt = deliveredAt;
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}


