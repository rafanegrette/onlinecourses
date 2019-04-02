package com.rafanegrette.bootstrap;

import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.rafanegrette.model.Course;
import com.rafanegrette.model.President;
import com.rafanegrette.model.University;
import com.rafanegrette.model.User;
import com.rafanegrette.services.CourseService;
import com.rafanegrette.services.PresidentService;
import com.rafanegrette.services.UniversityService;
import com.rafanegrette.services.UserService;

@Component
public class OnlinecoursesBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final CourseService courseService;
	private final PresidentService presidentService;
	private final UniversityService universityService;
	private final UserService userService;
	
	private final String uniguajiraStr = "UniGuajira";
	
	public OnlinecoursesBootstrap(CourseService courseService, PresidentService presidentService,
			UniversityService universityService, UserService userService) {
		this.courseService = courseService;
		this.presidentService = presidentService;
		this.universityService = universityService;
		this.userService = userService;
	}



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		setUniversityToPresident();
		setCoursesToUniversity();
		setUsersToCourses();
	}



	private void setUniversityToPresident() {		
		University uniguajira = universityService.findByName(uniguajiraStr);
		President president = presidentService.findByUserName("MTESSIER"); 
		uniguajira.setPresident(president);
		universityService.edit(uniguajira);
		
	}



	private void setUsersToCourses() {
		String title = "Calculus";
		Set<Course> courses = courseService.findAll(); 
		String userName = "ralph";
		User student = userService.findByUserName(userName);
		student.setCourses(courses);
		
		userService.edit(student);
		
	}



	private void setCoursesToUniversity() {
		Set<Course> courses = courseService.findAll();
		University university = universityService.findByName(uniguajiraStr);
		courses.stream().forEach(course -> {
			course.setUniversity(university);
		});
		
		courseService.editAll(courses);
		
	}
	
	

}
