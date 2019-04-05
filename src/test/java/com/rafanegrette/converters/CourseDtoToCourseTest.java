package com.rafanegrette.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafanegrette.dto.CourseDto;
import com.rafanegrette.model.Course;

public class CourseDtoToCourseTest {
	
	final Long ID = 1L;
	final String TITLE = "Math";
	CourseDtoToCourse converter;
	
	@BeforeEach
	public void setUp() throws Exception{
		converter = new CourseDtoToCourse();
	}
	
	@Test
	public void testNull() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmpty() throws Exception {
		assertNotNull(converter.convert(new CourseDto()));
	}
	
	@Test
	public void convert() throws Exception {
		CourseDto courseDto = CourseDto.builder().id(ID).title(TITLE).build();
		
		Course course = converter.convert(courseDto) ;
		
		assertEquals(ID, course.getId());
		assertEquals(TITLE, course.getTitle());
	}

}
