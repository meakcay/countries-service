package com.countries.mehmet.service;

import java.util.Iterator;
import java.util.List;

import com.countries.mehmet.domain.Country;
import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.domain.Language;

public interface ICountryService {

	Iterator<Country> save(List<Country> countries);

	List<Country> list();
	
	List<Country> getCountriesThatSpeaks(Language language);
	
	List<Country> getCountriesThatSpends(Currencies currency);

	Country getCountry(Long id);

}
