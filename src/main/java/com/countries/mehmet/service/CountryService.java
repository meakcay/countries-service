package com.countries.mehmet.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countries.mehmet.domain.Country;
import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.domain.Language;
import com.countries.mehmet.repository.CountryRepository;
import lombok.Data;

@Data
@Service
public class CountryService implements ICountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> list() {

		List<Country> countries = countryRepository.findAll();
		
		return countries;
	}

	@Override
	public Iterator<Country> save(List<Country> countries) {
		return countryRepository.saveAll(countries).iterator();
	}

	@Override
	public Country getCountry(Long id) {
		
		Optional<Country> country = countryRepository.findById(id);
		
		return country.orElse(null);
	}

	@Override
	public List<Country> getCountriesThatSpeaks(Language language) {
		List<Country> countriesSpeaking = countryRepository.findByLanguages(language);
		return countriesSpeaking;
	}
	
	@Override
	public List<Country> getCountriesThatSpends(Currencies currency) {
		List<Country> countriesSpeaking = countryRepository.findByCurrencies(currency);
		return countriesSpeaking;
	}

}
