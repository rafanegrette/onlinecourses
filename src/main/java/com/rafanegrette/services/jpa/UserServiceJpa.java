package com.rafanegrette.services.jpa;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.CourseRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;
import com.rafanegrette.services.UserService;

@Service
public class UserServiceJpa implements UserService {
	
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final UniversityRepository universityRepository;

	public UserServiceJpa(UserRepository userRepository, CourseRepository courseRepository, 
							UniversityRepository universityRepository) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
		this.universityRepository = universityRepository;
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
		Course course = courseRepository.findById(courseId).orElse(null);
		Set<User> userSet = new HashSet<>();
		if (course != null) 
			userSet =  course.getUsers();
		return userSet;

	}

	public Set<User> findByUniversityId(Long universityId) {
		University university =universityRepository.findById(universityId).orElse(null);
		Set<User> userSet = new HashSet<>();
		if(university != null)
			for(Course course :university.getCourses()) {
				userSet.addAll(course.getUsers());
			}
			
		return userSet;		
	}

}
