package com.rafanegrette.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "courses")
@Entity
@NoArgsConstructor
public class University {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String country;
	@OneToOne
	private President president;
	
	@OneToMany
	private Set<Course> courses = new HashSet<>();

	@Builder
	public University(Long id, String name, String country, President president, Set<Course> courses) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.president = president;
		this.courses = courses;
	}
	
	
	

}
