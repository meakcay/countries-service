package com.countries.mehmet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countries.mehmet.domain.Country;
import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.domain.Language;

public interface CountryRepository extends JpaRepository<Country, Long> {

	List<Country> findByLanguages(Language language);
	
	List<Country> findByCurrencies(Currencies currencies);
	
}
