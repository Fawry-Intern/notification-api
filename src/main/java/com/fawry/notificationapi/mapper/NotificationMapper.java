package com.fawry.notificationapi.mapper;

import com.fawry.kafka.events.order_events.OrderCancelNotificationEvent;
import com.fawry.kafka.events.order_events.ShippingDetailsEvent;
import com.fawry.kafka.events.order_events.ShippingStatusEvent;
import com.fawry.kafka.events.user_events.RegisterEvent;
import com.fawry.kafka.events.user_events.ResetPasswordEvent;
import com.fawry.notificationapi.dto.enums.EventType;
import com.fawry.notificationapi.model.NotificationRequest;
import com.fawry.notificationapi.dto.enums.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {

    public NotificationRequest mapFromRegisterEventToNotificationRequest(RegisterEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.REGISTER)
                .event(event)
                .build();
    }

    public NotificationRequest mapFromResetPasswordEventToNotificationRequest(ResetPasswordEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.RESET_PASSWORD)
                .event(event)
                .build();
    }

    public NotificationRequest mapFromOrderCanceledEventToNotificationRequest(OrderCancelNotificationEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.ORDER_CANCELLATION)
                .event(event)
                .build();
    }
    public NotificationRequest mapFromReceivingOrderEventToNotificationRequest(ShippingStatusEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.EMAIL)
                .eventType(EventType.Receiving_Order)
                .event(event)
                .build();
    }
    public NotificationRequest mapFromShippingDetailsEventToNotificationRequest(ShippingDetailsEvent event) {
        return NotificationRequest.builder()
                .notificationType(NotificationType.REAL_TIME)
                .eventType(EventType.Real_Time_Shipping)
                .event(event)
                .build();
    }


}
