package com.rafanegrette.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
class CourseRepositoryIT {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Test
	void findByTitle() {
		String title = "Calculus";
		Course calculo = courseRepository.findByTitle(title);
		
		assertEquals(title, calculo.getTitle());
	}
	
	@Test
	void findByUsersInTest() {
		String title = "Calculus";
		String userName = "ralph";
		
		Course calculo = courseRepository.findByTitle(title);
		
		User rafa = userRepository.findByUserName(userName);
		
		assertEquals(userName, rafa.getUserName());
		assertNotNull(calculo.getUsers());
		
		calculo.getUsers().add(rafa);
		
		courseRepository.save(calculo);
		
		Set<User> users = new HashSet<>();
		users.add(rafa);
		Set<Course> courses = courseRepository.findByUsersIn(users);
		
		assertNotNull(courses);
		assertEquals(1, courses.size());
	}
	
	@Test
	void TestEditAll() {
		Set<Course> courses = new HashSet<>(); 
		courseRepository.findAll().forEach(course -> courses.add(course));
		
		courses.stream().forEach(course -> {
			course.setRelease(LocalDate.now());
		});
		
		courseRepository.saveAll(courses);
		
		Set<Course> returnCourses = new HashSet<>();
		courseRepository.findAll().forEach(course -> returnCourses.add(course));
		
		assertNotNull(returnCourses);
		returnCourses.stream().forEach(course -> {
			assertNotNull(course.getRelease());
		});
		
		
	}

}
