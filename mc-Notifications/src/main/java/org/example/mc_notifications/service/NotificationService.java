package org.example.mc_notifications.service;

import ru.org.example.common.events.NotificationDto;

public interface NotificationService {

    void createNotification(NotificationDto notificationDto);
}
