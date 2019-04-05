package com.rafanegrette.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryIT {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private CourseRepository courseRepository;
	
	String ralphName = "ralph";
	
	@Test
	void testSaveCourses() {
		Set<Course> courses = new HashSet<>();
		courseRepository.findAll().forEach(course -> courses.add(course));
		User user = userRepository.findByUserName(ralphName);
		user.setCourses(courses);
		userRepository.save(user);
		User returnedUser = userRepository.findByUserName(ralphName);
		
		assertNotNull(returnedUser);
		assertNotNull(returnedUser.getCourses());
		assertEquals(2, returnedUser.getCourses().size());		
	}
}
