package com.rafanegrette.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rafanegrette.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
