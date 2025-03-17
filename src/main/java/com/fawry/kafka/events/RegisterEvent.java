package com.fawry.kafka.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RegisterEvent extends BaseEvent{

    private final String username;

    public RegisterEvent(@JsonProperty("email") String email,
                         @JsonProperty("username") String username) {
        super(email);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "RegisterEvent{" +
                "email='" + super.getEmail() +
                "username='" + username + '\'' +
                '}';
    }
}
