package com.countries.mehmet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.countries.mehmet.domain.Country;
import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.domain.Language;
import com.countries.mehmet.exceptions.CountryNotFoundException;
import com.countries.mehmet.exceptions.CurrencyNotFoundException;
import com.countries.mehmet.exceptions.LanguageNotFoundException;
import com.countries.mehmet.service.ICountryService;
import com.countries.mehmet.service.ICurrencyService;
import com.countries.mehmet.service.ILanguageService;
import net.minidev.json.JSONObject;


@RestController
@RequestMapping("/countries")
public class CountryController {
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private ILanguageService languageService;
	
	@Autowired
	private ICurrencyService currencyService;

	
	public CountryController() {
		super();
	}
	
	@GetMapping("/list")
	public JSONObject list() {
		
		List<Country> countries = countryService.list();
		
		JSONObject result = new JSONObject();
		
		countries
			.stream()
			.forEach(s -> {
				result.put(s.getId().toString(), s.getCapitalName());
			});
		
		return result;
	}
	
	@GetMapping("/")
	public List<Link> listLinks() {
		
		List<Link> links = new ArrayList<Link>();
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).get(null));
		links.add(linkTo.withRel("country-with-id"));
		
		linkTo = 
				linkTo(methodOn(this.getClass()).list());
		links.add(linkTo.withRel("all-countries"));
		
		linkTo = linkTo(methodOn(this.getClass()).listCountriesSpeaking(null));
		links.add(linkTo.withRel("countries-speaking-language"));
		
		linkTo = linkTo(methodOn(this.getClass()).listCountriesSpending(null));
		links.add(linkTo.withRel("countries-spending-currency"));
		
		linkTo = linkTo(methodOn(LanguageController.class).list());
		links.add(linkTo.withRel("languages"));
		
		linkTo = linkTo(methodOn(CurrencyController.class).list());
		links.add(linkTo.withRel("currencies"));
		
		return links;
	}
	
	
	@GetMapping(value = "/list", params = "language")
	public List<String> listCountriesSpeaking(@RequestParam("language") String languageId) {
		
		Language language = languageService.findById(languageId);
		
		if(language == null) {
			throw new LanguageNotFoundException
			(String.format("Language with id:%s could not be found!", languageId));
		}
		
		List<Country> countries = countryService.getCountriesThatSpeaks(language);
		
		if(countries == null || countries.size() == 0) {
			throw new CountryNotFoundException(
					String.format("No country speaks language wit id:", languageId	));
		}
		
		return countries
				.stream()
				.map(Country::getCountryName)
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/list", params = "currency")
	public List<String> listCountriesSpending(@RequestParam("currency") String currencyId) {
		
		Currencies currency = currencyService.findById(currencyId.trim().toUpperCase());
		
		if(currency == null) {
			throw new CurrencyNotFoundException
			(String.format("Currency with id:%s could not be found!", currencyId));
		}
		
		List<Country> countries = countryService.getCountriesThatSpends(currency);
		
		if(countries == null || countries.size() == 0) {
			throw new CountryNotFoundException(
					String.format("No country spends %s", currency.getCurrencyName()	));
		}
		
		return countries
				.stream()
				.map(Country::getCountryName)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public EntityModel<Country> get(@PathVariable("id") Long id) {
		
		Country country = countryService.getCountry(id);
		
		if(country == null) {
			throw new CountryNotFoundException(
					String.format("Country with id-%d could not be found!", id));
		}
		
		EntityModel<Country> resource = EntityModel.of(country);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).get(id));
		resource.add(linkTo.withSelfRel());
		
		linkTo = 
				linkTo(methodOn(this.getClass()).list());
		resource.add(linkTo.withRel("all-countries"));
		
		linkTo = linkTo(methodOn(this.getClass()).listCountriesSpeaking(null));
		resource.add(linkTo.withRel("countries-speaking-language"));
		
		linkTo = linkTo(methodOn(this.getClass()).listCountriesSpending(null));
		resource.add(linkTo.withRel("countries-spending-currency"));
		
		linkTo = linkTo(methodOn(LanguageController.class).list());
		resource.add(linkTo.withRel("languages"));
		
		linkTo = linkTo(methodOn(CurrencyController.class).list());
		resource.add(linkTo.withRel("currencies"));
		
		
		return resource;
	}
	

}
