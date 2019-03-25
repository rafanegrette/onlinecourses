package com.rafanegrette.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity
public class User extends Person{
	
	@ManyToMany(mappedBy = "users")
	private Set<Course> courses = new HashSet<>();

	@Builder
	public User(Long id, String userName, String firstName, String lastName, 
			String email, Set<Course> courses) {
		
		super(id, userName, firstName, lastName,email);
		this.courses = courses;
	}
	
	
}
