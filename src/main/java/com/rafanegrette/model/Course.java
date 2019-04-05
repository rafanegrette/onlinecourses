package com.rafanegrette.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"users"})
@NoArgsConstructor
@Entity
public class Course {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String contentTable; 
	private LocalDate release;
	
	@ManyToOne
	private University university;
	
	@ManyToMany
	@JoinTable(name = "course_users", joinColumns = @JoinColumn(name = "course_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();

	@Builder
	public Course(Long id, String title, String contentTable, LocalDate release, University university,
			Set<User> users) {
		this.id = id;
		this.title = title;
		this.contentTable = contentTable;
		this.release = release;
		this.university = university;
		this.users = users;
	}

	
}
