package com.utility.utility.integration;

import org.springframework.stereotype.Service;

@Service
public class PaymentIntegrationService {

	public boolean processPayment(Long billId, String cardNumber, String expiryDate, String cvv) {

	    if (cardNumber == null) {
	        System.out.println("❌ Card number is null");
	        return false;
	    }

	    String cleanedCard = cardNumber.replaceAll("\\s", "");

	    if (cleanedCard.length() != 16) {
	        System.out.println("❌ Payment Integration: Invalid card number for bill " + billId);
	        return false;
	    }

	    if (cvv == null || !cvv.matches("\\d{3}")) {
	        System.out.println("❌ Payment Integration: Invalid CVV for bill " + billId);
	        return false;
	    }

	    String last4 = cleanedCard.substring(12);

	    System.out.println("📨 Payment Integration: Sending payment to third-party queue...");
	    System.out.println("   Bill ID: " + billId);
	    System.out.println("   Card: **** **** **** " + last4);
	    System.out.println("   Expiry: " + expiryDate);
	    System.out.println("✅ Payment Integration: Payment successfully sent to queue for bill " + billId);

	    return true;
	}
    // Simulates sending payment details to a third-party JMS queue
    }