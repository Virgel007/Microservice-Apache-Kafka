package org.example.mc_shipping.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_shipping.kafka.KafkaProducerService;
import org.example.mc_shipping.model.Shipping;
import org.example.mc_shipping.model.ShippingStatus;
import org.example.mc_shipping.service.ShippingService;
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
public class ShippingServiceImpl implements ShippingService {

    private final ConcurrentHashMap<UUID, Shipping> inMemoryPayments = new ConcurrentHashMap<>();

    private final KafkaProducerService kafkaProducerService;

    @Override
    public void createShipping(NotificationDto notificationDto) {
        log.info("Creating payment for order={}, user={}, amount={}", notificationDto.getOrderId(), notificationDto.getUserId(), notificationDto.getAmount());
        Shipping shipping = new Shipping();
        shipping.setShippingId(UUID.randomUUID());
        shipping.setOrderId(notificationDto.getOrderId());
        shipping.setUserId(notificationDto.getUserId());
        shipping.setAmount(notificationDto.getAmount());
        shipping.setShippingStatus(ShippingStatus.SHIPPED);
        shipping.setSuccess(false);
        shipping.setShippingTime(Instant.now());
        shipping.setDelete(false);
        log.info("Payment created: {}", shipping);
        inMemoryPayments.put(shipping.getShippingId(), shipping);
        checkingPaymentAndSendingPayment(shipping);
    }

    public void checkingPaymentAndSendingPayment(Shipping shipping) {
        /**
         * отправляем товар до вся логика
         */

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEventId(UUID.randomUUID());
        notificationDto.setUserId(shipping.getUserId());
        notificationDto.setOrderId(shipping.getOrderId());
        notificationDto.setAmount(shipping.getAmount());
        notificationDto.setContent(null);
        notificationDto.setSentTime(Instant.now());
        notificationDto.setServiceType(ServiceType.SHIPPING);

        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setNotificationData(notificationDto);
        kafkaProducerService.sendEvent(notificationEvent);
        log.info("Notification event sent: {}", notificationEvent);
    }
}
