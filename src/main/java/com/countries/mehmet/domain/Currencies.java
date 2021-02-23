package com.countries.mehmet.domain;

import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Currencies {

	@Id
	@Size(min = 3, max = 3)
	private String id;
	
	private String currencyName;

	public Currencies(Currency currencyCode) {
		super();
		this.currencyName = currencyCode.getDisplayName();
		id = currencyCode.getCurrencyCode();
	}

	public Currencies(String id) {
		super();
		this.id = id;
		
		try {
			Currency currency = Currency.getInstance(id);
			currencyName = currency.getDisplayName();
		} catch (Exception e) {
			
		}
		
	}
	
	
}
