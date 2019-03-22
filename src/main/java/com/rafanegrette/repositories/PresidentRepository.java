package com.rafanegrette.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rafanegrette.model.President;
import com.rafanegrette.model.University;

public interface PresidentRepository extends CrudRepository<President, Long> {
	
	President findByUniversity(University university);
}
