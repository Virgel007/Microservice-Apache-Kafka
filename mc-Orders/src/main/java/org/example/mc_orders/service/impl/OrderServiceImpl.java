package org.example.mc_orders.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.mc_orders.kafka.KafkaProducerService;
import org.example.mc_orders.model.Order;
import org.example.mc_orders.model.Product;
import org.example.mc_orders.service.OrderService;
import org.springframework.stereotype.Service;
import ru.org.example.common.events.NotificationDto;
import ru.org.example.common.events.NotificationEvent;
import ru.org.example.common.events.ServiceType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ConcurrentHashMap<UUID, Order> inMemoryOrders = new ConcurrentHashMap<>();

    private final KafkaProducerService kafkaProducerService;

    @Override
    public String createOrder() {
        Set<Product> products = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setProductId(UUID.randomUUID());
            product.setPrice(new java.math.BigDecimal(Math.round(100 + Math.random() * 1900)));
            product.setTitle(randomTitle());
            products.add(product);
        }

        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setUserId(UUID.randomUUID());
        order.setAmount(
                products.stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        order.setItems(products);

        inMemoryOrders.put(order.getUserId(), order);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEventId(UUID.randomUUID());
        notificationDto.setUserId(order.getUserId());
        notificationDto.setOrderId(order.getOrderId());
        notificationDto.setAmount(order.getAmount());
        notificationDto.setContent(randomTitle());
        notificationDto.setSentTime(Instant.now());
        notificationDto.setServiceType(ServiceType.ORDER);

        NotificationEvent notificationEvent = new NotificationEvent();
        notificationEvent.setEventType("event_order");
        notificationEvent.setNotificationData(notificationDto);
        kafkaProducerService.sendEvent(notificationEvent);
        return "Order created";
    }

    private String randomTitle() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            title.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return title.toString();
    }
}
