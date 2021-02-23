package com.countries.mehmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countries.mehmet.domain.Currencies;

public interface CurrencyRepository extends JpaRepository<Currencies, String> {

}
