package com.rafanegrette.services.jpa;

import java.util.Set;

import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.repositories.PresidentRepository;
import com.rafanegrette.services.PresidentService;

public class PresidentServiceJpa implements PresidentService {

	private final PresidentRepository presidentRepo;
	
	public PresidentServiceJpa(PresidentRepository presidentRepo) {
		this.presidentRepo = presidentRepo;
	}

	public President findById(Long id) {
		return presidentRepo.findById(id).orElse(null);
	}

	public Set<President> findAll() {
		return (Set<President>) presidentRepo.findAll();
	}

	public President create(President object) {
		return presidentRepo.save(object);
	}

	public President edit(President object) {
		return presidentRepo.save(object);
	}

	public void delete(President object) {
		presidentRepo.delete(object);

	}

	public President findByUniversity(University university) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<President> findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
