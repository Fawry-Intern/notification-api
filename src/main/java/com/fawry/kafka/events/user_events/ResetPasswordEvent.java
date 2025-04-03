package com.fawry.kafka.events.user_events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fawry.kafka.events.BaseEvent;

public class ResetPasswordEvent extends BaseEvent {
    private final String username;
    private final String linkToken;

    public ResetPasswordEvent(@JsonProperty("email") String email,
                              @JsonProperty("username") String username,
                              @JsonProperty("linkToken") String linkToken
    ) {
        super(email);
        this.username = username;
        this.linkToken = linkToken;
    }

    public String getUsername() {
        return username;
    }

    public String getLinkToken() {
        return linkToken;
    }


    @Override
    public String toString() {
        return "ResetPasswordEvent{" +
                "username='" + username + '\'' +
                ", linkToken='" + linkToken + '\'' +
                '}';
    }
}
