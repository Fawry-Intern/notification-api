package com.fawry.notificationapi.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "custom.mail")
public class EmailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private Properties properties = new Properties();
}
