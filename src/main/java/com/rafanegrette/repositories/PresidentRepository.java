package com.rafanegrette.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rafanegrette.model.President;

public interface PresidentRepository extends CrudRepository<President, Long> {

}
