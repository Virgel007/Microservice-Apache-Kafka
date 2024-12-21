package org.example.mc_payment.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class Payment {
    private UUID paymentId;
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private Boolean success;
    private Instant paymentTime;
    private Boolean delete;
}
