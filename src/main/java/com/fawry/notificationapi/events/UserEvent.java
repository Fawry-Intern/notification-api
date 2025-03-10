package com.fawry.notificationapi.events;

public record UserEvent(
        Long userId,
        String username,
        String email
) {
}
