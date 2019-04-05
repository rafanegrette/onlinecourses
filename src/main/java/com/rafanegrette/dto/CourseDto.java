package com.rafanegrette.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {
	private Long id;
	private String title;
	private String contentTable; 
	private LocalDate release;
	private UniversityDto university;
	private Set<UserDto> users = new HashSet<>();
	
	@Builder
	public CourseDto(Long id, String title, String contentTable, LocalDate release, UniversityDto university,
			Set<UserDto> users) {
		this.id = id;
		this.title = title;
		this.contentTable = contentTable;
		this.release = release;
		this.university = university;
		this.users = users;
	}
}
