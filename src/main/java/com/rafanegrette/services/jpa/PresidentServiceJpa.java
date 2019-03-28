package com.rafanegrette.services.jpa;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.CourseRepository;
import com.rafanegrette.repositories.PresidentRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;
import com.rafanegrette.services.PresidentService;

public class PresidentServiceJpa implements PresidentService {

	private final PresidentRepository presidentRepo;
	
	private final CourseRepository courseRepo;
	
	public PresidentServiceJpa(PresidentRepository presidentRepo, CourseRepository courseRepo) {
		this.presidentRepo = presidentRepo;
		this.courseRepo = courseRepo;
	}

	public President findById(Long id) {
		return presidentRepo.findById(id).orElse(null);
	}

	public Set<President> findAll() {
		return (Set<President>) presidentRepo.findAll();
	}

	public President create(President object) {
		return presidentRepo.save(object);
	}

	public President edit(President object) {
		return presidentRepo.save(object);
	}

	public void delete(President object) {
		presidentRepo.delete(object);

	}

	public President findByUniversity(University university) {		
		return presidentRepo.findByUniversity(university);
	}

	public Set<President> findByUser(User user) {
		
		Set<User> users = new HashSet<>();
		
		users.add(user);
		
		Set<Course> courses = courseRepo.findByUsersIn(users);
		Set<University> universities = courses.stream().map(course -> course.getUniversity()).collect(Collectors.toSet());
		Set<President> presidents = universities.stream().map(uni -> uni.getPresident()).collect(Collectors.toSet());
		
		return presidents;
	}

}
