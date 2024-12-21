package org.example.mc_notifications.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_notifications.model.Notification;
import org.example.mc_notifications.model.NotificationStatus;
import org.example.mc_notifications.service.NotificationService;
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
public class NotificationServiceImpl implements NotificationService {

    private final ConcurrentHashMap<UUID, Notification> inMemoryPayments = new ConcurrentHashMap<>();


    @Override
    public void createNotification(NotificationDto notificationDto) {
        log.info("Creating notification for order={}, user={}, amount={}", notificationDto.getOrderId(), notificationDto.getUserId(), notificationDto.getAmount());
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID());
        notification.setOrderId(notificationDto.getOrderId());
        notification.setUserId(notificationDto.getUserId());
        notification.setAmount(notificationDto.getAmount());
        notification.setNotificationStatus(NotificationStatus.SENT);
        notification.setNotificationTime(Instant.now());
        notification.setDelete(false);
        log.info("Notification created: {}", notification);
        inMemoryPayments.put(notification.getNotificationId(), notification);
    }
}
