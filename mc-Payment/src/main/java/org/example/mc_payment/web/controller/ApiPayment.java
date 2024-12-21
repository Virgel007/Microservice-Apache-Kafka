package org.example.mc_payment.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mc_payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class ApiPayment {

    @GetMapping
    public ResponseEntity<String> obtainPaymentStatus() {

        return ResponseEntity.ok("PaymentStatus");
    }

    @PostMapping
    public ResponseEntity<String> paymentConfirmation() {

        return ResponseEntity.ok("PaymentConfirmation");
    }
}