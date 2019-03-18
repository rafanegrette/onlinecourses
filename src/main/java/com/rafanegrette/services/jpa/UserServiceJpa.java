package com.rafanegrette.services.jpa;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.rafanegrette.model.User;
import com.rafanegrette.repositories.UserRepository;
import com.rafanegrette.services.UserService;

@Service
public class UserServiceJpa implements UserService {
	
	private final UserRepository userRepository;
	

	public UserServiceJpa(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public Set<User> findAll() {
		return (Set<User>) userRepository.findAll();
	}

	public User create(User object) {
		return userRepository.save(object);
	}

	public User edit(User object) {
		return userRepository.save(object);
	}

	public void delete(User object) {
		userRepository.delete(object);
	}

	public Set<User> findByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<User> findByUniversityId(Long universityId) {
		// TODO Auto-generated method stub
		return null;
	}

}
