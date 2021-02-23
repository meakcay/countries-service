package com.countries.mehmet.service;

import java.util.List;
import com.countries.mehmet.domain.Currencies;

public interface ICurrencyService {

	
	List<Currencies> list();
	
	Currencies findById(String id);
	
	boolean save(Currencies currency);

}
