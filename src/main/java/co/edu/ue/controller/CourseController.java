package co.edu.ue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Course;
import co.edu.ue.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Controlador para Curso", description = "Este es el controlador para los métodos de Curso")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class CourseController {

    @Autowired
    ICourseService service;

    // Mostrar todos los cursos
    @Operation(summary = "Obtener todos los cursos", description = "Devuelve una lista de todos los cursos disponibles.")
    @GetMapping(value="courses-all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> list = service.listAllCourse();
        int cantList = list.size();
        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(cantList));
        headers.add("test", "Hola");
        return new ResponseEntity<>(list, headers, HttpStatus.ACCEPTED);
    }

    // Consultar el curso por ID
    @Operation(summary = "Consultar curso por ID", description = "Devuelve un curso específico basado en su ID.")
    @GetMapping(value="courses-id")
    public ResponseEntity<Course> getByIdCourse(@Parameter(description = "ID del curso a consultar") @RequestParam("id") int id) {
        return new ResponseEntity<>(service.searchByIdCourse(id), HttpStatus.OK);
    }

    // Mostrar todos los cursos con IVA
    @Operation(summary = "Obtener todos los cursos con IVA", description = "Devuelve una lista de todos los cursos disponibles incluyendo IVA.")
    @GetMapping(value="courses-iva")
    public ResponseEntity<List<Course>> getAllCoursesWithIVA() {
        List<Course> list = service.listAllCourseIVA();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Añadir un nuevo curso
    @Operation(summary = "Añadir un nuevo curso", description = "Crea un nuevo curso en el sistema.")
    @PostMapping(value="course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        if (service.postByCourse(course)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // Actualizar STATUS del curso
    @Operation(summary = "Actualizar estado del curso", description = "Actualiza el estado (activo/inactivo) de un curso específico.")
    @PutMapping(value="course-status")
    public ResponseEntity<String> updateCourseStatus(
            @Parameter(description = "ID del curso a actualizar") @RequestParam("id") int id,
            @Parameter(description = "Nuevo estado del curso (activo/inactivo)") @RequestParam("status") String status) {
        
        if (!status.matches("^(activo|inactivo)$")) {
            return new ResponseEntity<>("Estado inválido. Solo se permiten 'activo' o 'inactivo'.", HttpStatus.BAD_REQUEST);
        }
        
        if (service.statusCourse(id, status)) {
            return new ResponseEntity<>("Estado del curso actualizado exitosamente.", HttpStatus.OK);
        }
        
        return new ResponseEntity<>("Curso no encontrado.", HttpStatus.NOT_FOUND);
    }

    // Actualizar campos del curso
    @Operation(summary = "Actualizar campos del curso", description = "Actualiza los campos de un curso específico.")
    @PutMapping(value="course")
    public ResponseEntity<String> updateCourse(
            @Parameter(description = "ID del curso a actualizar") @RequestParam("id") int id,
            @RequestBody Course updatedCourse) {
        
        Course existingCourse = service.searchByIdCourse(id);
        
        if (existingCourse == null) {
            return new ResponseEntity<>("Curso no encontrado.", HttpStatus.NOT_FOUND);
        }

        existingCourse.setCouName(updatedCourse.getCouName());
        existingCourse.setCouDescription(updatedCourse.getCouDescription());
        existingCourse.setCouPrice(updatedCourse.getCouPrice());

        if (updatedCourse.getCouStatus() != null) {
            String status = updatedCourse.getCouStatus();
            if (!status.matches("^(activo|inactivo)$")) {
                return new ResponseEntity<>("Estado inválido. Solo se permiten 'activo' o 'inactivo'.", HttpStatus.BAD_REQUEST);
            }
            existingCourse.setCouStatus(status);
        }

        if (updatedCourse.getTypeCourse() != null) {
            existingCourse.setTypeCourse(updatedCourse.getTypeCourse());
        }

        if (service.postByCourse(existingCourse)) {
            return new ResponseEntity<>("Curso actualizado exitosamente.", HttpStatus.OK);
        }
        
        return new ResponseEntity<>("Error al actualizar el curso.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Eliminar curso
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso específico basado en su ID.")
    @DeleteMapping(value="course")
    public ResponseEntity<String> deleteCourse(@Parameter(description="ID del curso a eliminar") 
                                               @RequestParam("id") int id) {
        
        if (service.deleteCourse(id)) {
            return new ResponseEntity<>("Curso eliminado exitosamente.", HttpStatus.OK);
        }
        
        return new ResponseEntity<>("Curso no encontrado.", HttpStatus.NOT_FOUND);
   }
}