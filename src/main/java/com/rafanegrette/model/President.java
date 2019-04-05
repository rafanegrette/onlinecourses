package com.rafanegrette.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Entity
public class President extends Person{
	
	private LocalDate since;
	
	@OneToOne
	private University university;
	
	@Builder
	public President(Long id, String userName, String firstName, String lastName, 
			String email, LocalDate since, University university) {
		
		super(id, userName, firstName, lastName, email);
		this.since = since;
		this.university = university;
	}
	
	

}
