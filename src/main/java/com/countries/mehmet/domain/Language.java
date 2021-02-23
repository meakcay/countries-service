package com.countries.mehmet.domain;

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
public class Language {
	
	@Id
	@Size(min = 3, max = 3)
	private String languageCode;
	
	private String languageName;

	public Language(String languageCode) {
		super();
		this.languageCode = languageCode;
	}
	
	
}
