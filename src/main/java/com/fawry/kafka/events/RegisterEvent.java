package com.fawry.kafka.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterEvent extends BaseEvent{


    public RegisterEvent(@JsonProperty("email") String email) {
        super(email);

    }

    @Override
    public String toString() {
        return "RegisterEvent{" +
                "email='" + super.getEmail() +
                "username='"  + '\'' +
                '}';
    }
}
