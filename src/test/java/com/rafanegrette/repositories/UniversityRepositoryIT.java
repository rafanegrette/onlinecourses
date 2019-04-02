package com.rafanegrette.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafanegrette.model.President;
import com.rafanegrette.model.University;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UniversityRepositoryIT {
	
	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private PresidentRepository presidentRepository;
	
	String universityName = "stanford";
	String presidentUserName = "MTESSIER";
	
	@Test
	void testFindByName() {
		
		
		University university = universityRepo.findByName(universityName);
		
		assertNotNull(university);
		assertEquals(universityName, university.getName());
		
		
	}
	
	@Test
	void testEditPresident() {
		University university = universityRepo.findByName(universityName);
		President president = presidentRepository.findByUserName(presidentUserName);
		university.setPresident(president);
		
		universityRepo.save(university);
		
		University returnUniversity = universityRepo.findByName(universityName);
		
		assertNotNull(returnUniversity);
		assertNotNull(returnUniversity.getPresident());
	}

}
