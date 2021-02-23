package com.countries.mehmet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.countries.mehmet.controller.CountryController;
import com.countries.mehmet.controller.CurrencyController;
import com.countries.mehmet.controller.LanguageController;

@SpringBootTest
class CountriesApplicationTests {

	@Autowired
	private CountryController countryController;
	
	@Autowired
	private LanguageController languageController;
	
	@Autowired
	private CurrencyController currencyController;
	
	@Test
	void contextLoads() {
		assertThat(countryController).isNotNull();
		assertThat(languageController).isNotNull();
		assertThat(currencyController).isNotNull();
	}

}
