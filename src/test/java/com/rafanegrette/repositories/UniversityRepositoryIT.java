package com.rafanegrette.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafanegrette.model.University;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UniversityRepositoryIT {
	
	@Autowired
	private UniversityRepository universityRepo;

	@Test
	void testFindByName() {
		String name = "stanford";
		
		University university = universityRepo.findByName(name);
		
		assertNotNull(university);
		assertEquals(name, university.getName());
		
		
	}

}
