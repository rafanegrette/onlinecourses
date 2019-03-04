package com.rafanegrette.service;

import java.util.Set;

import com.rafanegrette.model.Course;

public interface CourseService extends CrudService<Course, Long>{
	
	Set<Course> findByUniversityId(Long universityId);
	
	Set<Course> findByUserId(Long userId);
}
