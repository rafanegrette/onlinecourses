package com.rafanegrette.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class User extends Person{
	
	@ManyToMany(mappedBy = "users")
	private Set<Course> courses;

	@Builder
	public User(Long id, String userName, String firstName, String lastName, 
			String email, Set<Course> courses) {
		
		super(id, userName, firstName, lastName,email);
		this.courses = courses;
	}
	
	
}
