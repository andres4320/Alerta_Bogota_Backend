package co.edu.ue.dao;

import java.util.List;

import co.edu.ue.entity.Course;

public interface ICourseDao {

	List<Course> listCourses();
	Course searchById(int id);
	
	boolean postCourse(Course course);
	boolean statusCourse(int id, String status);
	boolean deleteCourse(int id);
    boolean updateCourse(int id, Course course);

}
