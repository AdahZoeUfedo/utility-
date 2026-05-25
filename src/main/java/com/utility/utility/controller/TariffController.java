package com.utility.utility.controller;

import com.utility.utility.model.Tariff;
import com.utility.utility.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/tariffs")
public class TariffController {

	private final TariffService tariffService;

	public TariffController(
	        TariffService tariffService
	) {
	    this.tariffService = tariffService;
	}

    @GetMapping
    public String viewTariffs(Model model) {
        List<Tariff> tariffs = tariffService.getAllTariffs();
        model.addAttribute("tariffs", tariffs);
        return "tariffs";
    }
}