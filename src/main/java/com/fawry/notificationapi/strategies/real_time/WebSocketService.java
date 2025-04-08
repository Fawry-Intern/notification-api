package com.fawry.notificationapi.strategies.real_time;

import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.strategy.NotificationStrategy;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebSocketService implements NotificationStrategy {

    private  final SimpMessagingTemplate   template;


    @Override
    public void sendNotification(NotificationRequest request) {
        template.convertAndSend("/topic/order", request.event());
    }
}
