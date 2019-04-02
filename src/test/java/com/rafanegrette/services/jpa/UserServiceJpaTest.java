package com.rafanegrette.services.jpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.CourseRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;
import com.rafanegrette.services.jpa.UserServiceJpa;

@ExtendWith(MockitoExtension.class)
class UserServiceJpaTest {
	
	@InjectMocks
	UserServiceJpa userServiceJpa;
	
	@Mock
	UserRepository userRepository;	
	
	@Mock
	CourseRepository courseRepository;
	
	@Mock
	UniversityRepository universityRepository;
	
	User user;
	
	final Long userId = 1L;
	final String userName = "ralph";
	
	@BeforeEach
	void setUp() throws Exception {
		user = User.builder().id(userId).userName(userName).build();
	}

	@Test
	void testFind() {
		
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		
		User findedUser = userServiceJpa.findById(userId);
		
		assertEquals(userName, findedUser.getUserName());
	}

	@Test
	void testFindAll() {
		Set<User> returnUserSet = new HashSet<>();
		returnUserSet.add(User.builder().id(1L).userName("jpaul").build());
		returnUserSet.add(User.builder().id(2L).userName("francis").build());
		returnUserSet.add(User.builder().id(3L).userName("benedict").build());		
				//userServiceJpa.findAll();
		when(userRepository.findAll()).thenReturn(returnUserSet);
		
		Set<User> userSet = userServiceJpa.findAll();
		
		assertNotNull(userSet);
		assertEquals(3, userSet.size());
	}

	@Test
	void testCreate() {		
		
		when(userRepository.save(any())).thenReturn(user);
		
		User savedUser = userServiceJpa.create(user);
		
		assertNotNull(savedUser);
		
		assertEquals(user.getFirstName(), savedUser.getFirstName());
		
		verify(userRepository).save(any());
	}

	@Test
	void testEdit() {
		//User user = userServiceJpa.findById(userId);
		String firstName = "ralph";
		user.setFirstName(firstName);
		
		when(userRepository.save(any())).thenReturn(user);
		
		User savedUser = userServiceJpa.edit(user);
		
		assertEquals(firstName, savedUser.getFirstName());
		verify(userRepository).save(any());
	}

	@Test
	void testDelete() {
		userServiceJpa.delete(user);		
		
		verify(userRepository, times(1)).delete(any());;
	}

	@Test
	void testFindByCourseId() {		
		Long courseId = 1l;
		Course course = Course.builder().id(courseId).title("Calculus").users(new HashSet<>()).build();		
		Long userId2 = 2l;
		User user2 = User.builder().id(userId2).userName("matilda").courses(new HashSet<>()).build();
		course.getUsers().add(user2);
		
		User user = User.builder().id(userId).userName(userName).courses(new HashSet<>()).build();
		course.getUsers().add(user);
		
		when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
				
		assertEquals(2, userServiceJpa.findByCourseId(courseId).size());
	}

	@Test
	void testFindByUniversityId() {
		Long courseId = 1l;
		Course course = Course.builder().id(courseId).title("Calculus").users(new HashSet()).build();
		University university = University.builder().id(2L).name("Universidad de La Guajira").courses(new HashSet<>()).build();
		university.getCourses().add(course);
		User user = User.builder().id(userId).userName(userName).courses(new HashSet<>()).build();
		//user.getCourses().add(course);
		course.getUsers().add(user);
		
		when(universityRepository.findById(anyLong())).thenReturn(Optional.of(university));
		Set<User> users = userServiceJpa.findByUniversityId(university.getId());
		
		assertNotNull(users);
		assertEquals(1, users.size());
		
	}	
	
	@Test 
	void testFindByUseName(){
		when(userRepository.findByUserName(userName)).thenReturn(user);
		
		User returnUser = userServiceJpa.findByUserName(userName);
		
		assertNotNull(returnUser);
		assertEquals(userName, returnUser.getUserName());
	}
}
