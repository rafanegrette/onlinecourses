package com.rafanegrette.services.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mock;
import org.mockito.InjectMocks;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.PresidentRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UniversityServiceJpaTest {

	@Mock
	PresidentRepository presidentRepository;
	
	@Mock
	UniversityRepository universityRepo;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UniversityServiceJpa universityServiceJpa;
	
	University university;
	
	final Long universityId = 1L;
	final String universityName = "UniGuajira";
	
	@BeforeEach
	void setUp() throws Exception {
		university = University.builder().id(universityId).name(universityName).courses(new HashSet<>()).build();
	}

	@Test
	void testFind() {
		
		when(universityRepo.findById(any())).thenReturn(Optional.of(university));
		
		University findedUniversity = universityServiceJpa.findById(universityId);
		
		assertEquals(universityName, findedUniversity.getName());
		
		verify(universityRepo, times(1)).findById(anyLong());
		
	}

	@Test
	void testFindAll() {
		Set<University> returnUniSet = new HashSet<>();
		returnUniSet.add(University.builder().id(1l).name("Uniguajira").build());
		returnUniSet.add(University.builder().id(2l).name("MIT").build());
		
		when(universityRepo.findAll()).thenReturn(returnUniSet);
		
		Set<University> universitySet = universityServiceJpa.findAll();
		assertEquals(2, universitySet.size());
		verify(universityRepo, times(1)).findAll();
	}

	@Test
	void testCreate() {
		when(universityRepo.save(any())).thenReturn(university);
		
		University universityToSave = University.builder().id(universityId).name(universityName).build();
		University savedUniversity = universityServiceJpa.create(universityToSave);
		
		assertEquals(universityName, savedUniversity.getName());
		verify(universityRepo, times(1)).save(any());
	}

	@Test
	void testEdit() {
		String name = "Uninorte";
		university.setName(name);
		
		when(universityRepo.save(any())).thenReturn(university);
		University savedUniversity = universityServiceJpa.edit(university);
		
		assertEquals(name, savedUniversity.getName());
		verify(universityRepo, times(1)).save(any());
	}

	@Test
	void testDelete() {
		universityServiceJpa.delete(university);
		
		verify(universityRepo, times(1)).delete(any());
	}

	@Test
	void testFindByPresidentId() {
		Long presidentId = 1L;
		String lastName = "Tessier-Lavigne";
		President president = President.builder().id(presidentId).lastName(lastName).build();
		university.setPresident(president);
		president.setUniversity(university);
		
		when(presidentRepository.findById(presidentId)).thenReturn(Optional.of(president));
		
		University returnedUniversity = universityServiceJpa.findByPresidentId(presidentId);
		
		assertNotNull(returnedUniversity);
		assertNotNull(returnedUniversity.getPresident());
		assertEquals(lastName, returnedUniversity.getPresident().getLastName());
	}

	@Test
	void testFindByUserId() {
		Course course = Course.builder().id(1L).title("Calculus").build();
		User user = User.builder().id(1L).userName("matilda").courses(new HashSet<>()).build();
		user.getCourses().add(course);
		
		university.getCourses().add(course);
		
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		Set<University> universitySet = universityServiceJpa.findByUserId(user.getId());
		
		assertNotNull(universitySet);
		assertEquals(1, universitySet.size());
	}
	
	@Test
	void testFindByName() {
		
		when(universityRepo.findByName(universityName)).thenReturn(university);
		
		University returnedUniversity = universityServiceJpa.findByName(universityName);
		
		assertEquals(university.getId(), returnedUniversity.getId());
		
		verify(universityRepo, times(1)).findByName(any());
		
	}

}
