package com.countries.mehmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.countries.mehmet.domain.Country;
import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.domain.Language;
import com.countries.mehmet.service.ICountryService;
import com.countries.mehmet.service.ICurrencyService;
import com.countries.mehmet.service.ILanguageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CountriesCommandLineRunner implements CommandLineRunner {

	private static final Logger log =
			LoggerFactory.getLogger(CountriesCommandLineRunner.class);
	
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private ILanguageService languageService;
	
	@Override
	public void run(String... args) throws Exception {
		
		getCurrencies();
		
		getLanguages();
		
		getCountries();
	}

	private void getCurrencies() {
		Currency.getAvailableCurrencies()
		.stream()
		.forEach( s-> 
				{
					Currencies currency = new Currencies(s);
					currencyService.save(currency);
				}
				
				);
	}

	private void getCountries() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>(){};
		
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");
		
		try {
			List<Country> countries = mapper.readValue(inputStream, typeReference);
			countryService.save(countries);
			log.info(String.format("%d countries saved to H2 database", countries.size()));
			
		} catch (Exception e) {
			log.error("Failed to save countries to db :" + e.getMessage());
		}
	}
	
	private void getLanguages() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Language>> typeReference = new TypeReference<List<Language>>(){};
		
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/languages.json");
		
		try {
			List<Language> languages = mapper.readValue(inputStream, typeReference);
			languageService.save(languages);
			log.info(String.format("%d languages saved to H2 database", languages.size()));
			
		} catch (Exception e) {
			log.error("Failed to save languages to db :" + e.getMessage());
		}
	}

}
