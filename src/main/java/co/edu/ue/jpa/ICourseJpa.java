package co.edu.ue.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.Course;

public interface ICourseJpa extends JpaRepository<Course, Integer> {

}
