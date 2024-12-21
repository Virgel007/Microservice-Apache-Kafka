package org.example.mc_shipping.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_shipping.service.ShippingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.org.example.common.events.NotificationEvent;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private final ShippingService shippingService;

    private static final String TOPIC = "payed_orders";

    @KafkaListener(topics = TOPIC, groupId = "shipping-event-group")
    public void listen(NotificationEvent notificationEvent) {
        log.info("Received notification event from topic {}: {}", TOPIC, notificationEvent);
        shippingService.createShipping(notificationEvent.getNotificationData());
    }
}