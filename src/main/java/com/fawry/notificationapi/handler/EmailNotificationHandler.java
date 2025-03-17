package com.fawry.notificationapi.handler;

import com.fawry.notificationapi.model.NotificationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;


@Service
public class EmailNotificationHandler implements NotificationHandler {

    private final Logger log = LoggerFactory.getLogger(EmailNotificationHandler.class);
    private final JavaMailSender mailSender;

    @Value("${source.sender.email}")
    private String SOURCE_SENDER_MAIL;

    public EmailNotificationHandler(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(request.email());
            mailMessage.setSubject("I will Hack you, fawry الحرافيش system");
            mailMessage.setText("رمضان كريم وكل عام وأنتم بخير!\n" +
                    "\n" +
                    "      *     \n" +
                    "     ***    \n" +
                    "    *****   \n" +
                    "  ********* \n" +
                    " ************\n" +
                    "***************\n" +
                    " ************\n" +
                    "  ********* \n" +
                    "    *****   \n" +
                    "     ***");

            mailMessage.setFrom(SOURCE_SENDER_MAIL);
            mailSender.send(mailMessage);

            log.info("Email sent to {} with subject: {}", request.email(), "Hello muhammad");
        } catch (MailSendException e) {
            log.error("Failed to send email to {}: {}", request.email(), e.getMessage());
        }
    }
}
