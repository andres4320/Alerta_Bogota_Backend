package co.edu.ue.service;

import java.math.BigDecimal;
import java.util.List;

import co.edu.ue.entity.Course;

public interface ICourseService {

	List<Course> listAllCourse();
	List<Course> listAllCourseIVA();
	Course searchByIdCourse(int id);
	boolean postByCourse(Course course);
	boolean statusCourse(int id, String status);

}
