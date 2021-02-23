package com.countries.mehmet.controller;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.countries.mehmet.domain.Language;
import com.countries.mehmet.service.ILanguageService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/languages")
public class LanguageController {
	
	private ILanguageService languageService;

	
	public LanguageController(ILanguageService languageService) {
		super();
		this.languageService = languageService;
	}
	
	@GetMapping("/list")
	public MappingJacksonValue list() {
		
		List<Language> countries = languageService.list();
		
		SimpleBeanPropertyFilter filter 
			= SimpleBeanPropertyFilter.filterOutAllExcept("id", "countryName");
	
		FilterProvider filters 
			= new SimpleFilterProvider().addFilter("country-filter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(countries);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/{id}")
	public MappingJacksonValue get(@PathVariable("id") Long id) {
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(null);
		
		return mapping;
	}
	

}
