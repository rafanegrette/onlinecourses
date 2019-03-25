package com.rafanegrette.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rafanegrette.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUserName(String userName);

}
