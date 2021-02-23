package com.countries.mehmet.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String countryName;

	private String capitalName;

	private Continent continent;

	@ManyToMany
	private Set<Currencies> currencies;

	@ManyToMany
	private Set<Language> languages;

	private boolean independent;

}
