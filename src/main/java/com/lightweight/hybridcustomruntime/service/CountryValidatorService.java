package com.lightweight.hybridcustomruntime.service;

import java.util.Optional;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Service;

import com.lightweight.hybridcustomruntime.Application;
import com.lightweight.hybridcustomruntime.entity.CountryValidator;
import com.lightweight.hybridcustomruntime.repositories.CountryValidatorRepository;

@Service
public class CountryValidatorService {

	public String getValidity(String id) {
		GenericApplicationContext context = Application.getInstance().getContext();
		CountryValidatorRepository repository = context.getBean(CountryValidatorRepository.class);
		Optional<CountryValidator> result = repository.findById(id);
		return result.isPresent() ? result.get().getValidity() : "";
	}
}