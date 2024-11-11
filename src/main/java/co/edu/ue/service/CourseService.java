package co.edu.ue.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.ICourseDao;
import co.edu.ue.entity.Course;

@Service
public class CourseService implements ICourseService {

	@Autowired
	ICourseDao dao;
	private final float IVA_19=0.19f;
	
	@Override
	public List<Course> listAllCourse() {
		return dao.listCourses();
	}

	@Override
	public List<Course> listAllCourseIVA() {
		List<Course> courses = dao.listCourses();
	    return courses.stream()
	        .map(course -> {
	            BigDecimal totalPriceWithIVA = course.getCouPrice().multiply(BigDecimal.valueOf(1 + IVA_19));
	            totalPriceWithIVA = totalPriceWithIVA.setScale(2, RoundingMode.HALF_UP);
	            Course courseWithIVA = new Course();
	            courseWithIVA.setCouId(course.getCouId());
	            courseWithIVA.setCouDescription(course.getCouDescription());
	            courseWithIVA.setCouName(course.getCouName());
	            courseWithIVA.setCouPrice(totalPriceWithIVA);
	            courseWithIVA.setCouStatus(course.getCouStatus());
	            return courseWithIVA;
	        })
	        .collect(Collectors.toList());
	}

	@Override
	public Course searchByIdCourse(int id) {
		return dao.searchById(id);
	}

	@Override
	public boolean postByCourse(Course course) {
		return dao.postCourse(course);
	}

	@Override
	public boolean statusCourse(int id, String status) {
		return dao.statusCourse(id, status);
	}
}
