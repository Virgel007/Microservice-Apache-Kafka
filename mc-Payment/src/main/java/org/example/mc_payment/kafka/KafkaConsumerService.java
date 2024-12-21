package org.example.mc_payment.kafka;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_payment.service.PaymentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.org.example.common.events.NotificationEvent;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private final PaymentService paymentService;

    private static final String TOPIC = "new_orders";
// containerFactory = "kafkaListenerContainerFactory"
    @KafkaListener(topics = TOPIC, groupId = "payment-event-group")
    public void listen(NotificationEvent notificationEvent) {
        log.info("Received notification event from topic {}: {}", TOPIC, notificationEvent);
        paymentService.createPayment(notificationEvent.getNotificationData());
    }
}