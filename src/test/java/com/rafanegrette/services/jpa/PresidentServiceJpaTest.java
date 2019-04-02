package com.rafanegrette.services.jpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.CourseRepository;
import com.rafanegrette.repositories.PresidentRepository;

@ExtendWith(MockitoExtension.class)
class PresidentServiceJpaTest {
	
	@Mock
	PresidentRepository presidentRepo;
	
	@Mock
	CourseRepository courseRepository;
	
	@InjectMocks
	PresidentServiceJpa presidentServiceJpa;
	
	President president;
	
	final Long presidentId = 1L;
	final String lastName = "Tessier-Lavigne";

	@BeforeEach
	void setUp() throws Exception {
		president = President.builder().id(presidentId).lastName(lastName).build();
	}

	@Test
	void testFind() {
		when(presidentRepo.findById(any())).thenReturn(Optional.of(president));
		
		President findedPresident = presidentServiceJpa.findById(presidentId);
		
		assertEquals(lastName, findedPresident.getLastName());
		
		verify(presidentRepo, times(1)).findById(anyLong());
	}

	@Test
	void testFindAll() {
		Set<President> returnPresiSet = new HashSet<>();
		returnPresiSet.add(President.builder().id(2L).lastName("Reif").build());
		returnPresiSet.add(President.builder().id(3L).lastName("Bacow").build());
		
		when(presidentRepo.findAll()).thenReturn(returnPresiSet);
		
		Set<President> presiSet = presidentServiceJpa.findAll();
		
		assertNotNull(presiSet);
		assertEquals(2, presiSet.size());
		
		verify(presidentRepo, times(1)).findAll();
	}

	@Test
	void testCreate() {
		
		when(presidentRepo.save(any())).thenReturn(president);
		
		President presidentToSave= President.builder().id(presidentId).lastName(lastName).build();
		
		President savedPresident = presidentServiceJpa.create(presidentToSave);
		
		assertNotNull(savedPresident);
		
		assertEquals(lastName, savedPresident.getLastName());
		
		verify(presidentRepo, times(1)).save(any());
	}

	@Test
	void testEdit() {
		String lastName = "Bacow";
		president.setLastName(lastName);
		
		when(presidentRepo.save(any())).thenReturn(president);
		
		President savedPresident = presidentServiceJpa.edit(president);
		
		assertNotNull(savedPresident);
		assertEquals(lastName, savedPresident.getLastName());
		verify(presidentRepo, times(1)).save(any());
	}

	@Test
	void testDelete() {
		presidentServiceJpa.delete(president);
		
		verify(presidentRepo, times(1)).delete(any());
	}

	@Test
	void testFindByUniversity() {
		University university = University.builder().id(1L).name("stanford").president(president).build();
		
		when(presidentRepo.findByUniversity(university)).thenReturn(president);
		President returnPresident = presidentServiceJpa.findByUniversity(university);
		
		assertNotNull(returnPresident);
		
		assertEquals(lastName, returnPresident.getLastName());
	}

	@Test
	void testFindByUsers() {
		
		Course course = Course.builder().id(1L).title("Calculus").build();
		University university = University.builder().id(1L).name("stanford").president(president).build();
		course.setUniversity(university);
		
		User user = User.builder().id(1L).userName("matilda").courses(new HashSet<>()).build();
		user.getCourses().add(course);
		Set<Course> courses = new HashSet<>();
		courses.add(course);
		
		when(courseRepository.findByUsersIn(any())).thenReturn(courses);
		Set<President> presidentSet = presidentServiceJpa.findByUser(user);
		
		assertNotNull(presidentSet);
		assertEquals(1, presidentSet.size());
	}

}
