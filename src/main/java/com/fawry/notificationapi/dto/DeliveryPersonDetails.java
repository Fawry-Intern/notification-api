package com.fawry.notificationapi.dto;

import lombok.Builder;

@Builder
public record DeliveryPersonDetails(
        Long personId,
        String name,
        String email,
        String phoneNumber,
        String address
){ }
