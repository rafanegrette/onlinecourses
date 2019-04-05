package com.rafanegrette.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafanegrette.dto.PresidentDto;
import com.rafanegrette.model.President;

class PresidentDtoToPresidentTest {

	final Long ID = 1L;
	final String userName = "pjhonson";
	PresidentDtoToPresident converter;
	
	@BeforeEach
	void setUp() throws Exception {
		converter = new PresidentDtoToPresident();
	}
	
	@Test
	public void testNull() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmpty() throws Exception{
		assertNotNull(converter.convert(new PresidentDto()));
	}
	@Test
	void testConverter() throws Exception{
		PresidentDto presidentDto = PresidentDto.builder().id(ID).userName(userName).build();
		
		President president = converter.convert(presidentDto);
		
		assertEquals(ID, president.getId());
		assertEquals(userName, president.getUserName());
	}

}
