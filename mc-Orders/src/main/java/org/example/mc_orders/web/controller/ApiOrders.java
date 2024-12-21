package org.example.mc_orders.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class ApiOrders {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<String> createOrder() {
        log.info("Started createOrder");
        /**
         * Просто дергаем ручку для проверки
         */
        String result = orderService.createOrder();
        log.info("Finished createOrder: {}", result);
        return ResponseEntity.ok(result);
    }
}