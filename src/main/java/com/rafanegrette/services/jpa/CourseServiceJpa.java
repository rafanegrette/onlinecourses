package com.rafanegrette.services.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.CourseRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;
import com.rafanegrette.services.CourseService;

@Service
public class CourseServiceJpa implements CourseService {

	private CourseRepository courseRepository;
	
	private UniversityRepository universityRepository;
	
	private UserRepository userRepository;

	public CourseServiceJpa(CourseRepository courseRepository, UniversityRepository universityRepository, UserRepository userRepository) {
		this.courseRepository = courseRepository;
		this.universityRepository = universityRepository;
		this.userRepository = userRepository;
	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public Set<Course> findAll() {
		Set<Course> courses = new HashSet<>();
		courseRepository.findAll().forEach(course -> {
			courses.add(course);
		});
		
		return courses;
	}

	public Course create(Course object) {		
		return courseRepository.save(object);
	}

	public Course edit(Course object) {
		return courseRepository.save(object);
	}

	public void delete(Course object) {
		courseRepository.delete(object);

	}

	public Set<Course> findByUniversityId(Long universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		if (university != null)
			return university.getCourses();
		else
			return new HashSet<Course>();
	}

	public Set<Course> findByUserId(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if(user != null)
			return user.getCourses();
		else
			return new HashSet<Course>();
	}

	@Override
	public Course findByTitle(String title) {		
		return courseRepository.findByTitle(title);
	}

	@Override
	public void editAll(Set<Course> courses) {
		courseRepository.saveAll(courses);
		
	}

}
