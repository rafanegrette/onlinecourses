package com.rafanegrette.services;

import java.util.Set;

import com.rafanegrette.model.User;

public interface UserService extends CrudService<User, Long>{
	
	Set<User> findByCourseId(Long courseId);
	
	Set<User> findByUniversityId(Long universityId);

	User findByUserName(String userName);

}
