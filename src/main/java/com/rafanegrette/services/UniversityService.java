package com.rafanegrette.services;

import java.util.Set;

import com.rafanegrette.model.University;
import com.rafanegrette.model.User;

public interface UniversityService extends CrudService<University, Long>{
	
	University findByPresidentId(Long presidentId);
	
	Set<University> findByUserId(User user);

}
