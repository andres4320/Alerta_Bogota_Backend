package co.edu.ue.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ue.entity.CategoriasIncidencia;
import co.edu.ue.service.ICategoriaService;
import co.edu.ue.util.Tools; // Asumiendo que esta clase sigue siendo útil
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Categorias", description = "Controlador para la gestión de Categorias")
@RestController
@RequestMapping(value = "api/Category")
public class CategoriaController {
    
    @Autowired
    ICategoriaService service;

    // Listar todas las categorias
    @GetMapping(value = "ListCategory")
    public ResponseEntity<List<CategoriasIncidencia>> getAllCategorias() {
        List<CategoriasIncidencia> list = service.listarTodasCategorias();
        int cantList = list.size();
        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(cantList));
        return new ResponseEntity<>(list, headers, HttpStatus.ACCEPTED);
    }

    // Endpoint para buscar categorias por nombre
    @GetMapping(value = "searchByNameCategory")
    public ResponseEntity<?> getByNombreCategoria(@RequestParam("nombre_categoria") String nombre_categoria) {
        if (nombre_categoria == null || nombre_categoria.trim().isEmpty()) {
            return new ResponseEntity<>("Error, el nombre de la categoría no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }

        List<CategoriasIncidencia> categorias = service.buscarNombreCategoria(nombre_categoria);
        if (categorias.isEmpty()) {
            return new ResponseEntity<>("No se encontraron categorías para el nombre: " + nombre_categoria, HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(categorias.size()));
        return new ResponseEntity<>(categorias, headers, HttpStatus.OK);
    }
    
   // Crear nueva categoría
   @PostMapping(value = "createCategory")
   public ResponseEntity<String> postCategoria(@RequestBody CategoriasIncidencia categoria) {
       if (!Tools.verificarExpresionesCategorias(categoria)) { // Asumiendo que tienes una validación similar
           return new ResponseEntity<>("Todos los campos deben ser diligenciados correctamente", HttpStatus.BAD_REQUEST);
       }
       if (service.crearCategoria(categoria)) {
           return new ResponseEntity<>("La categoría ha sido creada con éxito", HttpStatus.CREATED);
       }
       return new ResponseEntity<>("Error interno al guardar la categoría.", HttpStatus.CONFLICT);
   }

   // Eliminar categoría por ID
   @DeleteMapping(value = "DeleteCategory")
   public ResponseEntity<String> deleteCategoriaById(@RequestParam("id") int id) {
       if (service.eliminarCategoria(id)) {
           return new ResponseEntity<>("Categoría con ID: " + id + " eliminada con éxito", HttpStatus.OK);
       }
       return new ResponseEntity<>("Error al intentar eliminar la categoría", HttpStatus.CONFLICT);
   }

   // Actualizar categoría
   @PutMapping(value = "UpdateCategory")
   public ResponseEntity<String> updateCategoriaById(@RequestBody CategoriasIncidencia categoria) {
       if (!Tools.verificarExpresionesCategorias(categoria)) { // Asumiendo que tienes una validación similar
           return new ResponseEntity<>("Error, los datos deben estar correctamente diligenciados.", HttpStatus.BAD_REQUEST);
       }
       if (service.actualizarCategoria(categoria.getCategoriaId(), categoria)) {
           return new ResponseEntity<>("Categoría actualizada correctamente", HttpStatus.OK);
       }
       return new ResponseEntity<>("Error interno al actualizar la categoría", HttpStatus.CONFLICT);
   }
}