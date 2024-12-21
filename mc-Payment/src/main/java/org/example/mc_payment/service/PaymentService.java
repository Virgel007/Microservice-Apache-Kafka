package org.example.mc_payment.service;

import org.example.mc_payment.model.Payment;
import ru.org.example.common.events.NotificationDto;

public interface PaymentService {

    void createPayment(NotificationDto notificationDto);
}
