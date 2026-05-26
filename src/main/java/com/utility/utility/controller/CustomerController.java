package com.utility.utility.controller;
import java.security.Principal;

import com.utility.utility.model.Customer;
import com.utility.utility.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.utility.utility.dto.request.RegisterCustomerRequestDTO;
import com.utility.utility.mapper.CustomerMapper;
import jakarta.validation.Valid;
@Controller
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(
	        CustomerService customerService
	) {
	    this.customerService = customerService;
	}

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
    	model.addAttribute(
    	        "customer",
    	        new RegisterCustomerRequestDTO(
    	                "",
    	                "",
    	                ""
    	        )
    	);
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(
            @Valid @ModelAttribute RegisterCustomerRequestDTO requestDTO
    ) {

        Customer customer = CustomerMapper.toEntity(requestDTO);

        customerService.save(customer);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        // Retrieve currently logged-in user's email
        String email = principal.getName();
        customerService.findByEmail(email).ifPresent(customer ->
                model.addAttribute("customer", customer)
        );
        return "dashboard";
    }
}
