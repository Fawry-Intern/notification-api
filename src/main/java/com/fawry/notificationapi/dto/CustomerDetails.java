package com.fawry.notificationapi.dto;

import lombok.Builder;

@Builder
public record CustomerDetails(
        String governorate,
        String city,
        String name,
        String address,
        String email,
        String phone
){
}
