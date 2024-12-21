package org.example.mc_shipping.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class Shipping {
    private UUID shippingId;
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private ShippingStatus shippingStatus;
    private Boolean success;
    private Instant shippingTime;
    private Boolean delete;
}
