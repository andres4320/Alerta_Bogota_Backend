package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "api")
public class IncidenceController {
    @Autowired
    IIncidenceService service;

    // Listar todas las incidencias
    @GetMapping(value = "incidencias")
    public ResponseEntity<List<Incidencia>> getAllIncidencias() {
        List<Incidencia> list = service.listarTodasIncidencias();
        int cantList = list.size();
        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(cantList));
        return new ResponseEntity<>(list, headers, HttpStatus.ACCEPTED);
    }
    // Endpoint para buscar incidencias por localidad
    @GetMapping(value = "consultarIncidenciasPorLocalidad")
    public ResponseEntity<?> getByLocalidad(@RequestParam("localidad") String localidad) {
        if (localidad == null || localidad.trim().isEmpty()) {
            return new ResponseEntity<String>("Error, la localidad no puede estar vacía.", HttpStatus.BAD_REQUEST);
        }

        List<Incidencia> incidencias = service.buscarPorLocalidad(localidad);
        if (incidencias.isEmpty()) {
            return new ResponseEntity<String>("No se encontraron incidencias para la localidad: " + localidad, HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(incidencias.size()));
        return new ResponseEntity<List<Incidencia>>(incidencias, headers, HttpStatus.OK);
    }
	
    // Crear nueva incidencia
    @PostMapping(value = "crearIncidencia")
    public ResponseEntity<String> postIncidencia(@RequestBody Incidencia incidencia) {
        if (!Tools.verificarExpresionesIncidencias(incidencia)) {
            return new ResponseEntity<>("Todos los campos deben ser diligenciados correctamente", HttpStatus.BAD_REQUEST);
        }
        if (service.crearIncidencia(incidencia)) {
            return new ResponseEntity<>("La incidencia ha sido creada con éxito", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error interno al guardar la incidencia.", HttpStatus.CONFLICT);
    }
    // Eliminar incidencia por ID
    @DeleteMapping(value = "eliminarIncidencia")
    public ResponseEntity<String> deleteIncidenciaById(@RequestParam("id") int id) {
        if (service.eliminarIncidencia(id)) {
            return new ResponseEntity<>("Incidencia con ID: " + id + " eliminada con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al intentar eliminar la incidencia", HttpStatus.CONFLICT);
    }

    // Actualizar incidencia
    @PutMapping(value = "actualizarIncidencia")
    public ResponseEntity<String> updateIncidenciaById(@RequestBody Incidencia incidencia) {
        if (!Tools.verificarExpresionesIncidencias(incidencia)) {
            return new ResponseEntity<>("Error, los datos deben estar correctamente diligenciados.", HttpStatus.BAD_REQUEST);
        }
        if (service.actualizarIncidencia(incidencia.getIncidenciaId(), incidencia)) {
            return new ResponseEntity<>("Incidencia actualizada correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error interno al actualizar la incidencia", HttpStatus.CONFLICT);
    }

}
