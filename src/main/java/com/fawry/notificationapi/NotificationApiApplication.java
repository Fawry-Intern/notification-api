package com.fawry.notificationapi;

import com.fawry.notificationapi.entities.Notification;
import com.fawry.notificationapi.model.NotificationType;
import com.fawry.notificationapi.service.PushNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

//	@Component
//	public class AppStarter implements CommandLineRunner {
//
//
//		private final Logger log = LoggerFactory.getLogger(AppStarter.class);
//		private final JavaMailSender mailSender;
//		private final PushNotificationService pushNotificationService;
//
//		private final String SOURCE_SENDER_MAIL = "muhammadhussein2312@gmail.com";
//
//		public AppStarter(JavaMailSender mailSender, PushNotificationService pushNotificationService) {
//			this.mailSender = mailSender;
//            this.pushNotificationService = pushNotificationService;
//        }
//
//
//		@Override
//		public void run(String... args) throws Exception {
//
////			Notification notification = new Notification();
////			notification.setNotificationId(null);
////			notification.setMessage("Hello muhammad");
////			notification.setRead(false);
////			notification.setUserId(234L);
////			notification.setNotificationType(NotificationType.PUSH);
////
////			pushNotificationService.storeNotification(notification);
//
////			try {
////				SimpleMailMessage mailMessage = new SimpleMailMessage();
////				mailMessage.setTo("muhammedmuhammed2309za@gmail.com");
////				mailMessage.setSubject("Hello muhammad");
////				mailMessage.setText("Test email sender");
////				mailMessage.setFrom(SOURCE_SENDER_MAIL);
////				mailSender.send(mailMessage);
////
////				log.info("Email sent to {} with subject: {}", "muhammadhussein2312@gmail.com", "Hello muhammad");
////			} catch (MailSendException e) {
////				log.error("Failed to send email to {}: {}", "muhammadhussein2312@gmail.com", e.getMessage());
////			}
//
////			Thread thread1 = new Thread(() -> {
////				for (var i = 0; i < 1000_000; i ++){
////					example.increment();
////				}
////			});
////
////			Thread thread2= new Thread(() -> {
////				for (var i = 0; i < 1000_000; i ++){
////					example.increment();
////				}
////			});
////			thread1.start();
////			thread2.start();
////			thread1.join();
////			thread2.join();
////
////			System.out.println(example.getCount());
//		}
//	}

}
