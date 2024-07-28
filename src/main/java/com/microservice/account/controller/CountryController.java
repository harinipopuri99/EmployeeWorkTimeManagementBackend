package com.microservice.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Country;
import com.microservice.account.model.Region;
import com.microservice.account.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@PostMapping("/api/cap/country/add")
	public Country postCountry(@RequestBody Country country) {
		return countryService.postCountry(country);
	}
	
	@GetMapping("/api/cap/country/all")
	public List<Country> getAllRegions() {
		return countryService.getAllCountries();
	}
}
/* */