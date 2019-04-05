package com.rafanegrette.converters;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafanegrette.dto.UserDto;
import com.rafanegrette.model.User;

public class UserDtoToUserTest {
	
	final Long ID = 1l;
	final String USERNAME = "rvalas";
	UserDtoToUser converter;
	
	@BeforeEach
	public void setUp() throws Exception{
		converter = new UserDtoToUser();
	}
	
	@Test
	public void testNull() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmpty() throws Exception{
		assertNotNull(converter.convert(new UserDto()));
	}
	
	@Test
	public void convert() throws Exception{
		UserDto userDto = UserDto.builder().id(ID).userName(USERNAME).build();
		
		User user = converter.convert(userDto);
		
		
	}
}
