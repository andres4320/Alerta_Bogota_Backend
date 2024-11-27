package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ue.entity.User;
import co.edu.ue.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controlador para Usuarios", description = "Este es el controlador para manejar usuarios")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un nuevo usuario en la aplicación.")
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.postUser(user)) {
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error al registrar el usuario", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.listAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Buscar usuario por ID", description = "Devuelve un usuario específico por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<User> searchByIdUser(@PathVariable int id) {
        User user = userService.searchByIdUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente.")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
        if (userService.updateUser(id, user)) {
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