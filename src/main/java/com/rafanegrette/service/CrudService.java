package com.rafanegrette.service;

import java.util.Set;

public interface CrudService <T, ID>{
	
	T find(ID id);
	
	Set<T> findAll();
	
	T create(T object);
	
	T edit(T object);
	
	void delete(T object);


}
