package co.edu.ue.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ue.entity.Favorito;
import co.edu.ue.service.IFavoritoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Favoritos", description = "Controlador para la gestión de Favorites")
@RestController
@RequestMapping(value = "api/Favorites")
public class FavoritoController {

    @Autowired
    IFavoritoService service;

    // Listar todos los favoritos
    @GetMapping(value = "Listfavorites")
    public ResponseEntity<List<Favorito>> getAllFavoritos() {
        List<Favorito> list = service.listarTodosFavoritos();
        HttpHeaders headers = new HttpHeaders();
        headers.add("cant_elements", String.valueOf(list.size()));
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    // Crear nuevo favorito
    @PostMapping(value = "CreateFavorites")
    public ResponseEntity<String> postFavorito(@RequestBody Favorito favorito) {
        if (service.crearFavorito(favorito)) {
            return new ResponseEntity<>("El favorito ha sido creado con éxito", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error interno al guardar el favorito.", HttpStatus.CONFLICT);
    }

   // Eliminar favorito por ID
   @DeleteMapping(value = "DeleteFavorites")
   public ResponseEntity<String> deleteFavoritoById(@RequestParam("id") int id) {
       if (service.eliminarFavorito(id)) {
           return new ResponseEntity<>("Favorito con ID: " + id + " eliminado con éxito", HttpStatus.OK);
       }
       return new ResponseEntity<>("Error al intentar eliminar el favorito", HttpStatus.CONFLICT);
   }
}