package com.utility.utility.controller;
import com.utility.utility.model.Customer;

import com.utility.utility.dto.response.BillResponseDTO;
import com.utility.utility.integration.BillingIntegrationService;
import com.utility.utility.integration.PaymentIntegrationService;
import com.utility.utility.model.Bill;
import com.utility.utility.service.BillService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {

	private final BillService billService;
	private final BillingIntegrationService billingIntegrationService;
	private final PaymentIntegrationService paymentIntegrationService;

	public BillController(
	        BillService billService,
	        BillingIntegrationService billingIntegrationService,
	        PaymentIntegrationService paymentIntegrationService
	) {

	    this.billService = billService;
	    this.billingIntegrationService = billingIntegrationService;
	    this.paymentIntegrationService = paymentIntegrationService;
	}
    @GetMapping("/customer/{customerId}")
    public String viewBills(@PathVariable Long customerId, Model model) {
        // Simulate fetching bills from external billing system
        billingIntegrationService.generateSimulatedBillsForCustomer(customerId);

        List<BillResponseDTO> bills = billService.getBillDTOsByCustomerId(customerId);
        model.addAttribute("bills", bills);
        model.addAttribute("customerId", customerId);
        return "bills";
    }

    @GetMapping("/pay/{billId}")
    public String showPayPage(@PathVariable Long billId, Model model) {
        billService.findById(billId).ifPresent(bill -> model.addAttribute("bill", bill));
        return "pay";
    }

    @PostMapping("/pay/{billId}")
    public String processPay(@PathVariable Long billId,
                             @RequestParam String cardNumber,
                             @RequestParam String expiryDate,
                             @RequestParam String cvv,
                             Model model) {

        boolean paymentSuccess = paymentIntegrationService.processPayment(
                billId, cardNumber, expiryDate, cvv
        );

        if (paymentSuccess) {
            billService.findById(billId).ifPresent(bill -> billService.markAsPaid(bill));
            model.addAttribute("message", "Payment successful!");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Payment failed. Please check your card details.");
            model.addAttribute("messageType", "error");
        }

        Bill bill = billService.getBillOrThrow(billId);

        Long customerId = bill.getCustomer().getId();

        List<BillResponseDTO> bills =
                billService.getBillDTOsByCustomerId(customerId);

        model.addAttribute("bills", bills);

        return "bills";
    }
}