package com.countries.mehmet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countries.mehmet.domain.Currencies;
import com.countries.mehmet.repository.CurrencyRepository;
import lombok.Data;

@Data
@Service
public class CurrencyService implements ICurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public Currencies findById(String id) {
		Optional<Currencies> currency = currencyRepository.findById(id);
		
		return currency.orElse(null);
	}

	@Override
	public boolean save(Currencies currencies) {
		Currencies savedCurrency = currencyRepository.save(currencies);
		return savedCurrency != null;
	}



	@Override
	public List<Currencies> list() {
		return currencyRepository.findAll();
	}

}
