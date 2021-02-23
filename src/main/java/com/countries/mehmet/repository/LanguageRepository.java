package com.countries.mehmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countries.mehmet.domain.Language;

public interface LanguageRepository extends JpaRepository<Language, String> {

	
	
}
