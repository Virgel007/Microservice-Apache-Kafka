package org.example.mc_notifications.kafka;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.mc_notifications.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.org.example.common.events.NotificationEvent;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private final NotificationService notificationService;

    private static final String TOPIC = "sent_orders";
    @KafkaListener(topics = TOPIC, groupId = "notifications-event-group")
    public void listen(NotificationEvent notificationEvent) {
        log.info("Received notification event from topic {}: {}", TOPIC, notificationEvent);
        notificationService.createNotification(notificationEvent.getNotificationData());
    }
}