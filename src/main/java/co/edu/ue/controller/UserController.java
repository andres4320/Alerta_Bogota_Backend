package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ue.entity.Usuario;
import co.edu.ue.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Usuarios", description = "Este es el controlador para manejar usuarios")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

   @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un nuevo usuario en la aplicación.")
   @PostMapping("/register")
   public ResponseEntity<String> registerUser(@RequestBody Usuario usuario) {
       if (userService.postUser(usuario)) {
           return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
       }
       return new ResponseEntity<>("Error al registrar el usuario", HttpStatus.BAD_REQUEST);
   }
   
   @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
   @GetMapping("/all-users")
   public ResponseEntity<List<Usuario>> listAllUsers() {
       List<Usuario> users = userService.listAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
   }
   
   @Operation(summary = "Buscar usuario por ID", description = "Devuelve un usuario específico por su ID.")
   @GetMapping("/search-user/{id}")
   public ResponseEntity<Usuario> searchByIdUser(@PathVariable int id) {
	   Usuario user = userService.searchByIdUser(id);
       if (user != null) {
           return new ResponseEntity<>(user, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   
   @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente.")
   @PutMapping("/update/{id}")
   public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody Usuario usuario) {
       if (userService.updateUser(id, usuario)) {
           return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
       }
       return new ResponseEntity<>("Error al actualizar el usuario", HttpStatus.BAD_REQUEST);
   }
   
   @Operation(summary = "Eliminar usuario", description = "Elimina un usuario específico por su ID.")
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteUser(@PathVariable int id) {
       if (userService.deleteUser(id)) {
           return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
       }
       return new ResponseEntity<>("Error al eliminar el usuario", HttpStatus.NOT_FOUND);
   }
}