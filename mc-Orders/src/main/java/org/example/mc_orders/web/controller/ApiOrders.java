package org.example.mc_orders.web.controller;


import lombok.RequiredArgsConstructor;
import org.example.mc_orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class ApiOrders {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<String> createOrder() {
        /**
         * Просто дергаем ручку для проверки
         */
        return ResponseEntity.ok(orderService.createOrder());
    }
}