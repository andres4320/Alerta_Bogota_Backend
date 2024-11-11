package co.edu.ue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Course;
import co.edu.ue.service.ICourseService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api")
public class CourseController {

	@Autowired
	ICourseService service;
	
	@GetMapping(value="courses-all")
	public  ResponseEntity<List<Course>> getAllCourses () {
		List<Course> list = service.listAllCourse();
		//1. Agregar el header a nuevos datos
		int cantList = list.size();
		HttpHeaders headers = new HttpHeaders();
		headers.add("cant_elements", String.valueOf(cantList));
		headers.add("test", "Hola");
		//2. Creamos la respuesta de Tipo ResponseEntity
		return new ResponseEntity<List<Course>>(list, headers, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="courses-id")
	public ResponseEntity<Course> getByIdCourse(@RequestParam("id") int id) {
		return new ResponseEntity<Course>(service.searchByIdCourse(id),HttpStatus.OK);
	}
	
	@PostMapping(value="course")
	public ResponseEntity <Course> getByIdCourse(@RequestBody Course course) {
		if (service.postByCourse(course)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping(value = "course-status")
	public ResponseEntity<String> updateCourseStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
	    if (!status.matches("^(activo|inactivo)$")) {
	        return new ResponseEntity<>("Estado inválido. Solo se permiten 'activo' o 'inactivo'.", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (service.statusCourse(id, status)) {
	        return new ResponseEntity<>("Estado del curso actualizado exitosamente.", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Curso no encontrado.", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="courses-iva")
	public ResponseEntity<List<Course>> getAllCoursesWithIVA() {
	    List<Course> list = service.listAllCourseIVA();
	    return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
