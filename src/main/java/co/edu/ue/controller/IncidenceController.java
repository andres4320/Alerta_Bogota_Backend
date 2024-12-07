package co.edu.ue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Incidencia;
import co.edu.ue.service.IIncidenceService;
import co.edu.ue.util.Tools;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Incidencias", description = "Controlador para la gestión de incidencias")
@RestController
@RequestMapping("/api/incidences")
public class IncidenceController {
    @Autowired
    IIncidenceService service;

    @GetMapping(value = "listIncidences")
    public ResponseEntity<List<Incidencia>> getAllIncidencias() {
        List<Incidencia> list = service.listIncidences();
        int cantList = list.size();
        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(cantList));
        return new ResponseEntity<>(list, headers, HttpStatus.ACCEPTED);
    }
  
    @GetMapping(value = "searchByLocality")
    public ResponseEntity<?> getByLocalidad(@RequestParam("localidad") String localidad) {
        if (localidad == null || localidad.trim().isEmpty()) {
            return new ResponseEntity<String>("Error, la localidad no puede estar vacía.", HttpStatus.BAD_REQUEST);
        }

        List<Incidencia> incidencias = service.searchByLocality(localidad);
        if (incidencias.isEmpty()) {
            return new ResponseEntity<String>("No se encontraron incidencias para la localidad: " + localidad, HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(incidencias.size()));
        return new ResponseEntity<List<Incidencia>>(incidencias, headers, HttpStatus.OK);
    }
	
    @GetMapping(value = "searchByCategory")
    public ResponseEntity<?> getByCategoria(@RequestParam("categoria") String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            return new ResponseEntity<>("Error, la categoría no puede estar vacía.", HttpStatus.BAD_REQUEST);
        }
   
        List<Incidencia> incidencias = service.searchByCategory(nombreCategoria);
        if (incidencias.isEmpty()) {
            return new ResponseEntity<>("No se encontraron incidencias para la categoría: " + nombreCategoria, HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(incidencias.size()));
        return new ResponseEntity<>(incidencias, headers, HttpStatus.OK);
    }

    @GetMapping(value = "searchByDate")
    public ResponseEntity<?> getByFecha(@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        System.out.println("Fecha normalizada para búsqueda: " + sqlDate);
    	List<Incidencia> incidencias = service.searchByDate(sqlDate);
        if (incidencias.isEmpty()) {
            return new ResponseEntity<>("No se encontraron incidencias para la fecha: " + sqlDate, HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(incidencias.size()));
        return new ResponseEntity<>(incidencias, headers, HttpStatus.OK);
    }    
    

    @PostMapping(value = "postIncidence")
    public ResponseEntity<Map<String, String>> postIncidencia(@RequestBody Incidencia incidencia) {
        Map<String, String> response = new HashMap<>();

        // Verificar las expresiones de la incidencia
        if (!Tools.verificarExpresionesIncidencias(incidencia)) {
            response.put("message", "Todos los campos deben ser diligenciados correctamente");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Intentar guardar la incidencia
        if (service.postIncidence(incidencia)) {
            response.put("message", "La incidencia ha sido creada con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        // Manejar el error interno
        response.put("message", "Error interno al guardar la incidencia.");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    
   /* @PostMapping(value = "postIncidence")
    public ResponseEntity<String> postIncidencia(@RequestBody Incidencia incidencia) {
        if (!Tools.verificarExpresionesIncidencias(incidencia)) {
            return new ResponseEntity<>("Todos los campos deben ser diligenciados correctamente", HttpStatus.BAD_REQUEST);
        }
        if (service.postIncidence(incidencia)) {
            return new ResponseEntity<>("La incidencia ha sido creada con éxito", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error interno al guardar la incidencia.", HttpStatus.CONFLICT);
    }
*/  
    @DeleteMapping(value = "deleteIncidence")
    public ResponseEntity<String> deleteIncidenciaById(@RequestParam("id") int id) {
        if (service.deleteIncidence(id)) {
            return new ResponseEntity<>("Incidencia con ID: " + id + " eliminada con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al intentar eliminar la incidencia", HttpStatus.CONFLICT);
    }
      
    @PutMapping(value = "updateIncidence")
    public ResponseEntity<String> updateIncidenciaById(@RequestBody Incidencia incidencia) {
        if (!Tools.verificarExpresionesIncidencias(incidencia)) {
            return new ResponseEntity<>("Error, los datos deben estar correctamente diligenciados.", HttpStatus.BAD_REQUEST);
        }
        if (service.updateIncidence(incidencia.getIncidenciaId(), incidencia)) {
            return new ResponseEntity<>("Incidencia actualizada correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error interno al actualizar la incidencia", HttpStatus.CONFLICT);
    }

}
