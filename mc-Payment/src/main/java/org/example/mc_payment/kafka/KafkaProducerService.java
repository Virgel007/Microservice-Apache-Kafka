package org.example.mc_payment.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.org.example.common.events.NotificationEvent;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducerService {

    private static final String NOTIFICATION_TOPIC = "payed_orders";

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void sendEvent(NotificationEvent notificationEvent) {
        kafkaTemplate.send(NOTIFICATION_TOPIC, notificationEvent.getEventType(), notificationEvent);
        log.info("sendEvent: topic={}, key={}, value={}", NOTIFICATION_TOPIC, notificationEvent.getEventType(), notificationEvent);
    }
}
