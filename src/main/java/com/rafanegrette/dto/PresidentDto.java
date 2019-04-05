package com.rafanegrette.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PresidentDto extends PersonDto {
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate since;
	private UniversityDto university;
	
	@Builder
	public PresidentDto(Long id, String userName, String firstName, String lastName, String email, LocalDate since,
			UniversityDto university) {
		super(id, userName, firstName, lastName, email);
		this.since = since;
		this.university = university;
	}
	
	
}
