package com.rafanegrette.services;

import java.util.Set;

public interface CrudService <T, ID>{
	
	T findById(ID id);
	
	Set<T> findAll();
	
	T create(T object);
	
	T edit(T object);
	
	void delete(T object);


}
