package org.example.mc_payment.service;

import ru.org.example.common.events.NotificationDto;

public interface PaymentService {

    void createPayment(NotificationDto notificationDto);
}
