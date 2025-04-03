package com.fawry.kafka.events.user_events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fawry.kafka.events.BaseEvent;

public class RegisterEvent extends BaseEvent {


    public RegisterEvent(@JsonProperty("email") String email) {
        super(email);

    }

    @Override
    public String toString() {
        return "RegisterEvent{" +
                "email='" + super.getEmail() +
                '}';
    }
}
