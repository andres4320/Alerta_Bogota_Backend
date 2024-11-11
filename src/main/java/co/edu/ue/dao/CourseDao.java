package co.edu.ue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Course;
import co.edu.ue.jpa.ICourseJpa;

@Repository
public class CourseDao implements ICourseDao {

	@Autowired
	ICourseJpa jpa;

	@Override
	public List<Course> listCourses() {
		return jpa.findAll();
	}

	@Override
	public Course searchById(int id) {
		return jpa.findById(id).orElse(null);
	}

	@Override
	public boolean postCourse(Course course) {
		if(!jpa.save(course).equals(null)) return true;
		return false;
	}

	@Override
	public boolean statusCourse(int id, String status) {
		Course course = searchById(id);
        if (course != null) {
            course.setCouStatus(status);
            jpa.save(course);
            return true;
        }
        return false;
	}
}
