package org.example.mc_payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_payment.kafka.KafkaProducerService;
import org.example.mc_payment.model.Payment;
import org.example.mc_payment.model.PaymentStatus;
import org.example.mc_payment.service.PaymentService;
import org.springframework.stereotype.Service;
import ru.org.example.common.events.NotificationDto;
import ru.org.example.common.events.NotificationEvent;
import ru.org.example.common.events.ServiceType;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ConcurrentHashMap<UUID, Payment> inMemoryPayments = new ConcurrentHashMap<>();

    private final KafkaProducerService kafkaProducerService;

    @Override
    public void createPayment(NotificationDto notificationDto) {
        log.info("Creating payment for order={}, user={}, amount={}", notificationDto.getOrderId(), notificationDto.getUserId(), notificationDto.getAmount());
        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID());
        payment.setOrderId(notificationDto.getOrderId());
        payment.setUserId(notificationDto.getUserId());
        payment.setAmount(notificationDto.getAmount());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setSuccess(false);
        payment.setPaymentTime(Instant.now());
        payment.setDelete(false);
        log.info("Payment created: {}", payment);
        inMemoryPayments.put(payment.getPaymentId(), payment);
        checkingPaymentAndSendingPayment(payment);
    }

    public void checkingPaymentAndSendingPayment(Payment payment) {
        /**
         * до этого мы уже все оплатили и подтвердили оплату.
         */

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEventId(UUID.randomUUID());
        notificationDto.setUserId(payment.getUserId());
        notificationDto.setOrderId(payment.getOrderId());
        notificationDto.setAmount(payment.getAmount());
        notificationDto.setContent(null);
        notificationDto.setSentTime(Instant.now());
        notificationDto.setServiceType(ServiceType.PAYMENT);

        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setNotificationData(notificationDto);
        kafkaProducerService.sendEvent(notificationEvent);
        log.info("Notification event sent: {}", notificationEvent);
    }
}
