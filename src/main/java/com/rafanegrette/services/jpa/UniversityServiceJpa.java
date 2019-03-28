package com.rafanegrette.services.jpa;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.PresidentRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;
import com.rafanegrette.services.UniversityService;

public class UniversityServiceJpa implements UniversityService {

	private UniversityRepository universityRepository;	
	private PresidentRepository presidentRepository;
	private UserRepository userRepository;
	
	
	public UniversityServiceJpa(UniversityRepository universityRepository, PresidentRepository presidentRepository
								, UserRepository userRepository) {
		this.universityRepository = universityRepository;
		this.presidentRepository = presidentRepository;
		this.userRepository = userRepository;
	}

	public University findById(Long id) {	
		return universityRepository.findById(id).orElse(null);
	}

	public Set<University> findAll() {
		return (Set<University>) universityRepository.findAll();
	}

	public University create(University object) {
		return universityRepository.save(object);
	}

	public University edit(University object) {
		return universityRepository.save(object);
	}

	public void delete(University object) {
		universityRepository.delete(object);

	}

	public University findByPresidentId(Long presidentId) {
		President president = presidentRepository.findById(presidentId).orElse(null);
		if (president != null)
			return president.getUniversity();
		else
			return null;
	}

	public Set<University> findByUserId(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		Set<University> universitySet = new HashSet<>();
		if(user != null) {
			universitySet = user.getCourses().stream().map(course -> course.getUniversity()).collect(Collectors.toSet());
		} 
		return universitySet;
	}

}
