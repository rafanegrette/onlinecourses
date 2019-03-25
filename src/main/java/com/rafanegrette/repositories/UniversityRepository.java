package com.rafanegrette.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rafanegrette.model.University;

public interface UniversityRepository extends CrudRepository<University, Long> {

	University findByName(String name);

}
