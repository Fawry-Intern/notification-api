package com.fawry.notificationapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {
    private final Logger log = LoggerFactory.getLogger(KafkaConfig.class);
//    @Bean
//    public DefaultErrorHandler kafkaErrorHandler() {
//        return new DefaultErrorHandler(
//                (record, exception) -> {
//                    // معالجة الرسالة الفاشلة
//                    log.error("Failed to process message: {}", record, exception);
//                },
//                new FixedBackOff(1000L, 3) // 3 محاولات مع تأخير ثابت 1 ثانية
//        );
//    }
}
