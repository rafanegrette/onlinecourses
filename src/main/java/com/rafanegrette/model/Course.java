package com.rafanegrette.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.rafanegrette.model.User.UserBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Course {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

}
