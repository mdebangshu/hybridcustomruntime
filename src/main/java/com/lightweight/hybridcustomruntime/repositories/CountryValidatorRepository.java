package com.lightweight.hybridcustomruntime.repositories;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.lightweight.hybridcustomruntime.entity.CountryValidator;

@EnableScan
public interface CountryValidatorRepository extends CrudRepository<CountryValidator, String> {

	Optional<CountryValidator> findById(String id);
}