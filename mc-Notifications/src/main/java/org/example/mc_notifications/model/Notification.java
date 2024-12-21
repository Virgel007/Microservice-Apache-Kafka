package org.example.mc_notifications.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class Notification {
    private UUID notificationId;
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private NotificationStatus notificationStatus;
    private Boolean success;
    private Instant notificationTime;
    private Boolean delete;
}
