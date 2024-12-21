package ru.org.example.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent implements BaseEvent {
    private String eventType = "NOTIFICATION_EVENT";

    private NotificationDto notificationData;

    @Override
    public String getEventType() {
        return eventType;
    }
}
