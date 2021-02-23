package com.countries.mehmet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.service.ICurrencyService;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
	
	private ICurrencyService currencyService;

	
	public CurrencyController(ICurrencyService currencyService) {
		super();
		this.currencyService = currencyService;
	}
	
	@GetMapping("/list")
	public List<Currencies> list() {
		return currencyService.list();
	}
	
	
	

}
