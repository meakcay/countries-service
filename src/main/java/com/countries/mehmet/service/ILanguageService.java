package com.countries.mehmet.service;

import java.util.Iterator;
import java.util.List;

import com.countries.mehmet.domain.Language;

public interface ILanguageService {

	List<Language> list();
	
	Iterator<Language> save(List<Language> languages);
	
	Language findById(String id);

}
