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
public class UniversityDto {
	private Long id;
	private String name;
	private String country;
	private PresidentDto president;
	private Set<CourseDto> courses = new HashSet<>();
	
	@Builder
	public UniversityDto(Long id, String name, String country, PresidentDto president, Set<CourseDto> courses) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.president = president;
		this.courses = courses;
	}
	
	
}
