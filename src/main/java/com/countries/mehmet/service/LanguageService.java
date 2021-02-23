package com.countries.mehmet.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countries.mehmet.domain.Language;
import com.countries.mehmet.repository.LanguageRepository;
import lombok.Data;

@Data
@Service
public class LanguageService implements ILanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Language findById(String id) {
		Optional<Language> language = languageRepository.findById(id);
		
		return language.orElse(null);
	}
	
	@Override
	public List<Language> list() {
		return languageRepository.findAll();
	}
	
	@Override
	public Iterator<Language> save(List<Language> languages) {
		return languageRepository.saveAll(languages).iterator();
	}

}
