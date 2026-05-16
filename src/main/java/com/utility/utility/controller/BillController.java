package com.utility.utility.controller;

import com.utility.utility.model.Bill;
import com.utility.utility.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/customer/{customerId}")
    public String viewBills(@PathVariable Long customerId, Model model) {
        List<Bill> bills = billService.getBillsByCustomerId(customerId);
        model.addAttribute("bills", bills);
        return "bills";
    }

    @GetMapping("/pay/{billId}")
    public String showPayPage(@PathVariable Long billId, Model model) {
        billService.findById(billId).ifPresent(bill -> model.addAttribute("bill", bill));
        return "pay";
    }

    @PostMapping("/pay/{billId}")
    public String processPay(@PathVariable Long billId) {
        billService.findById(billId).ifPresent(bill -> billService.markAsPaid(bill));
        return "redirect:/bills/customer/" + billId;
    }
}