package com.rafanegrette.services;

import java.util.Set;

import com.rafanegrette.model.Course;

public interface CourseService extends CrudService<Course, Long>{
	
	Set<Course> findByUniversityId(Long universityId);
	
	Set<Course> findByUserId(Long userId);

	Course findByTitle(String title);

	void editAll(Set<Course> courses);
}
