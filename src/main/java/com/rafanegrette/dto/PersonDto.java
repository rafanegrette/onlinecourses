package com.rafanegrette.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
}
