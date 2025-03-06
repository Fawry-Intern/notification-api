package com.fawry.notificationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

}
