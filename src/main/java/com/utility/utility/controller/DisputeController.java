package com.utility.utility.controller;

import com.utility.utility.service.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disputes")
public class DisputeController {

    @Autowired
    private DisputeService disputeService;

    @GetMapping("/new/{billId}")
    public String showDisputeForm(@PathVariable Long billId, Model model) {
        model.addAttribute("billId", billId);
        return "dispute";
    }

    @PostMapping("/submit/{billId}")
    public String submitDispute(@PathVariable Long billId,
                                @RequestParam String reason,
                                Model model) {
        String result = disputeService.submitDispute(billId, reason);
        model.addAttribute("message", result);
        return "dispute-result";
    }
}
