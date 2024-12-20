package org.example.mc_orders.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {
    private UUID productId;
    private BigDecimal price;
    private String title;
    private Boolean delete;
}
