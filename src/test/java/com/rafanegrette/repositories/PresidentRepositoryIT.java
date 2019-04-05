package com.rafanegrette.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafanegrette.model.President;
import com.rafanegrette.model.University;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PresidentRepositoryIT {
	
	@Autowired
	PresidentRepository presidentRepository;
	
	@Autowired
	UniversityRepository universityRepository;
	String universityName = "stanford";
	
	@Test
	void testFindByUniversity() {		
		University university = universityRepository.findByName(universityName);
		President president = President.builder().firstName("Rafael").lastName("Reif").university(university).build();
		presidentRepository.save(president);
		President returnPresident = presidentRepository.findByUniversity(university);
		
		assertEquals(university.getId(), returnPresident.getUniversity().getId());
	}
	
	@Test
	void testFindByUserName() {
		String userName = "MTESSIER";
		String firstName = "Mark";
		President president = presidentRepository.findByUserName(userName);
		
		assertNotNull(president);
		assertEquals(president.getFirstName(), firstName);
	}

}
