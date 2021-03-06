package com.rafanegrette.services;

import java.util.Set;

import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;

public interface PresidentService extends CrudService<President,Long>{
	
	President findByUniversity(University university);
	
	Set<President> findByUser(User user);

	President findByUserName(String string);

}
