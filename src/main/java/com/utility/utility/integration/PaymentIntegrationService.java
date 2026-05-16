package com.utility.utility.integration;

import org.springframework.stereotype.Service;

@Service
public class PaymentIntegrationService {

    // Simulates sending payment details to a third-party JMS queue
    public boolean processPayment(Long billId, String cardNumber, String expiryDate, String cvv) {

        // Simulate basic card validation
        if (cardNumber == null || cardNumber.replaceAll("\\s", "").length() != 16) {
            System.out.println("❌ Payment Integration: Invalid card number for bill " + billId);
            return false;
        }

        if (cvv == null || cvv.length() != 3) {
            System.out.println("❌ Payment Integration: Invalid CVV for bill " + billId);
            return false;
        }

        // Simulate sending to JMS queue
        System.out.println("📨 Payment Integration: Sending payment to third-party queue...");
        System.out.println("   Bill ID: " + billId);
        System.out.println("   Card: **** **** **** " + cardNumber.replaceAll("\\s", "").substring(12));
        System.out.println("   Expiry: " + expiryDate);
        System.out.println("✅ Payment Integration: Payment successfully sent to queue for bill " + billId);

        return true;
    }
}