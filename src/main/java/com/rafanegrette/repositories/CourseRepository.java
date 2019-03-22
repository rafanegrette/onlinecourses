package com.rafanegrette.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.User;

public interface CourseRepository extends CrudRepository<Course, Long> {
	
	Set<Course> findByUsersIn(Set<User> users);
}
