package org.example.mc_orders.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class Order {
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private Set<Product> items;
    private Boolean delete;
}
