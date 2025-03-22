package com.fawry;

import com.fawry.kafka.events.BaseEvent;
import com.fawry.kafka.events.RegisterEvent;
import com.fawry.kafka.events.ResetPasswordEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.dto.enums.NotificationType;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafkaRetryTopic;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafkaRetryTopic
@EnableScheduling
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

//	@Component
//	public class AppStarter implements CommandLineRunner {
//		private final NotificationService notificationService;
//
//        public AppStarter(NotificationService notificationService) {
//            this.notificationService = notificationService;
//        }
//
//
//        @Override
//		public void run(String... args) throws Exception {
//
//			ResetPasswordEvent event = new ResetPasswordEvent("muhammadhussein2312@gmail.com", "muhamma", "https://localhost:3432/dsf32asfd");
//
//			var re =  NotificationRequest.builder()
//					.notificationType(NotificationType.EMAIL)
//					.eventType(EventType.RESET_PASSWORD)
//					.event(event)
//					.build();
//
//			notificationService.sendNotification(re);
//		}
//	}
}
