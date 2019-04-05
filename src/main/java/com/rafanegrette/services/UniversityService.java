package com.rafanegrette.services;

import java.util.Set;

import com.rafanegrette.model.University;

public interface UniversityService extends CrudService<University, Long>{
	
	University findByPresidentId(Long presidentId);
	
	Set<University> findByUserId(Long userId);

	University findByName(String string);

}
