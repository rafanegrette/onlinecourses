package com.rafanegrette.services.jpa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.CourseRepository;
import com.rafanegrette.repositories.UniversityRepository;
import com.rafanegrette.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class CourseServiceJpaTest {
	
	@Mock
	CourseRepository courseRepository;
	
	@Mock
	UniversityRepository universityRepository;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	CourseServiceJpa courseServiceJpa;

	Course course;
	
	final Long courseId = 1L;
	final String title = "Calculus";
	
	@BeforeEach
	void setUp() throws Exception {
		course = Course.builder().id(courseId).title(title).users(new HashSet<>()).build();
	}
	
	@Test
	void testFind() {
		
		when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
		
		Course findedCourse = courseServiceJpa.findById(courseId);
		
		assertEquals(title, findedCourse.getTitle());
		
		verify(courseRepository, times(1)).findById(anyLong());
	}

	@Test
	void testFindAll() {
		Set<Course> returnedCourseSet = new HashSet<>();
		returnedCourseSet.add(Course.builder().id(1L).title("Caculus").build());
		returnedCourseSet.add(Course.builder().id(2L).title("Physics").build());
		returnedCourseSet.add(Course.builder().id(3L).title("Programming I").build());
		
		when(courseRepository.findAll()).thenReturn(returnedCourseSet);
		
		Set<Course> courseSet = courseServiceJpa.findAll();
		
		assertNotNull(courseSet);
		assertEquals(3, courseSet.size());
		
		verify(courseRepository, times(1)).findAll();
		
	}

	@Test
	void testCreate() {
		when(courseRepository.save(any())).thenReturn(course);
		
		Course savedCourse = courseServiceJpa.create(course);
		
		assertNotNull(savedCourse);
		
		assertEquals(title, savedCourse.getTitle());
		
		verify(courseRepository, times(1)).save(course);
	}

	@Test
	void testEdit() {
		String newTitle = "Integration"; 
		course.setTitle(newTitle);
		
		when(courseRepository.save(course)).thenReturn(course);
		
		Course savedCourse = courseServiceJpa.edit(course);
		
		assertNotNull(savedCourse);
		assertEquals(newTitle, savedCourse.getTitle());
		verify(courseRepository, times(1)).save(course);
	}

	@Test
	void testDelete() {
		courseServiceJpa.delete(course);
		
		verify(courseRepository, times(1)).delete(course);
	}

	@Test
	void testFindByUniversityId() {
		University university = University.builder().id(1L).name("Uniguajira").courses(new HashSet<>()).build();
		university.getCourses().add(course);		
		
		when(universityRepository.findById(anyLong())).thenReturn(Optional.of(university));
		
		Set<Course> returnedCourse = courseServiceJpa.findByUniversityId(university.getId());
		
		assertNotNull(returnedCourse);
		
		assertEquals(1, returnedCourse.size());
	}

	@Test
	void testFindByUserId() {		
		
		User andre = User.builder().id(1l).userName("andre").courses(new HashSet<>()).build();		
		course.getUsers().add(andre);
		andre.getCourses().add(course);
		
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(andre));
		
		Set<Course> courseSet = courseServiceJpa.findByUserId(andre.getId());
		
		assertNotNull(courseSet);
		assertEquals(1, courseSet.size());
	}

}
