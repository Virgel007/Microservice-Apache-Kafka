package org.example.mc_shipping.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
@Slf4j
public class ApiShipping {


    @GetMapping
    public ResponseEntity<String> deliveryStatus() {

        return ResponseEntity.ok("PaymentStatus");
    }
}