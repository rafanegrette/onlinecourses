package com.rafanegrette.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends PersonDto{
	
	private Set<CourseDto> courses = new HashSet<>();
	
	@Builder
	public UserDto(Long id, String userName, String firstName, String lastName,
				String email, Set<CourseDto> courses) {
		super(id, userName, firstName, lastName, email);
		this.courses = courses;
	}
}
