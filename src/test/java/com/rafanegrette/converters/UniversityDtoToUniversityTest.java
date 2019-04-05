package com.rafanegrette.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafanegrette.dto.UniversityDto;
import com.rafanegrette.model.University;

public class UniversityDtoToUniversityTest {
	
	final Long ID = 1L;
	final String NAME = "Unimagdalena";
	UniversityDtoToUniversity converter;
	
	@BeforeEach
	public void setUp() throws Exception{
		converter = new UniversityDtoToUniversity();
	}
	
	@Test
	public void testNull() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmpty() throws Exception {
		assertNotNull(converter.convert(new UniversityDto()));
	}
	@Test
	public void convert() throws Exception {
		UniversityDto universityDto = UniversityDto.builder().id(ID).name(NAME).build();
		
		University university = converter.convert(universityDto);
		
		assertEquals(ID, university.getId());
		assertEquals(NAME, university.getName());
	}
}
