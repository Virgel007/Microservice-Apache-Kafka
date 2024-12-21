package ru.org.example.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private UUID eventId;
    private UUID userId;
    private UUID orderId;
    private BigDecimal amount;
    private String content;
    private Instant sentTime;
    private ServiceType serviceType;
}
