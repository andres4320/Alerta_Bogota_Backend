package co.edu.ue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ue.entity.Usuario;
import co.edu.ue.service.IUserService;
import co.edu.ue.util.Tools;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Usuarios", description = "Este es el controlador para manejar usuarios")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @Operation(summary = "Registrar un usuario", description = "Crea una nueva cuenta en el sistema con nombres, correo y contraseña.")
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Usuario usuario) {
        Map<String, String> errores = Tools.verificarExpresionesUsuario(usuario);
        if (!errores.isEmpty()) {
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
            if (userService.postUser(usuario)) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Usuario registrado exitosamente");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (DataIntegrityViolationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("useEmail", "El correo electrónico ya está registrado. Por favor, utiliza otro.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Error al registrar el usuario");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
   
   @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
   @GetMapping(value = "/all-users")
   public ResponseEntity<List<Usuario>> listAllUsers() {
       List<Usuario> users = userService.listAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
   }
   
   @Operation(summary = "Buscar usuario por ID", description = "Devuelve un usuario específico por su ID.")
   @PostMapping("/search-user")
   public ResponseEntity<Usuario> searchByIdUser(@RequestBody Usuario usuario) {
       Usuario user = userService.searchByIdUser(usuario.getUsuarioId());
       if (user != null) {
           return new ResponseEntity<>(user, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   
   @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente.")
   @PutMapping(value = "/update")
   public ResponseEntity<Map<String, String>> updateUser(@RequestBody Usuario usuario) {
       Map<String, String> response = new HashMap<>();
       if (userService.updateUser(usuario.getUsuarioId(), usuario)) {
           response.put("message", "Usuario actualizado con éxito");
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       response.put("message", "Error al actualizar el usuario");
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   
   @Operation(summary = "Eliminar usuario", description = "Elimina un usuario específico por su ID.")
   @DeleteMapping(value = "/delete")
   public ResponseEntity<Map<String, String>> deleteUser(@RequestBody Usuario usuario) {
       Map<String, String> response = new HashMap<>();
       if (userService.deleteUser(usuario.getUsuarioId())) {
           response.put("message", "Usuario eliminado exitosamente");
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       response.put("message", "Error al eliminar el usuario");
       return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
   }
   
   @Operation(summary = "Verificar existencia de correo electrónico", description = "Verifica si un correo electrónico ya está registrado en el sistema. Retorna `true` si existe y `false` si no.")
   @GetMapping("/check-email")
   public ResponseEntity<Boolean> checkEmailExists(@RequestParam("email") String email) {
       boolean exists = userService.doesEmailExist(email);
       return ResponseEntity.ok(exists);
   }
   
   @Operation(summary = "Obtener token JWT por correo electrónico", description = "Genera y devuelve un token JWT para un usuario basado en su correo electrónico. Incluye el rol del usuario en el token. Responde con un estado 404 si el usuario no se encuentra.")
   @GetMapping("/get-token")
   public ResponseEntity<String> getTokenByEmail(@RequestParam("email") String email) {
       Usuario usuario = userService.findByUseEmail(email);
       if (usuario != null) {
           // Obtener el nombre del rol usando el rolId
           String rolNombre = getRoleNameById(usuario.getRolId());

           // Crear el token JWT
           String token = Jwts.builder()
                   .setSubject(usuario.getUseEmail())
                   .claim("authorities", List.of(rolNombre))
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                   .signWith(Keys.hmacShaKeyFor(Tools.CLAVE.getBytes()))
                   .compact();
           return ResponseEntity.ok(token);
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
   }
   
   private String getRoleNameById(int rolId) {
	    switch (rolId) {
	        case 1:
	            return "USUARIO";
	        case 2:
	            return "ADMINISTRADOR";
	        default:
	            return "UNKNOWN";
	    }
	}
   
   @Operation(summary = "Obtener estadísticas por roles", description = "Devuelve el número de usuarios agrupados por roles.")
   @GetMapping("/count-users-roles")
   public ResponseEntity<List<Map<String, Long>>> getStatsByRoles() {
       List<Map<String, Long>> stats = userService.countUsersByRole();
       return new ResponseEntity<>(stats, HttpStatus.OK);
   }

   @Operation(summary = "Obtener estadísticas por mes de registro", description = "Devuelve el número de usuarios agrupados por mes de registro.")
   @GetMapping("/registration-months")
   public ResponseEntity<List<Map<String, Long>>> getStatsByRegistrationMonth() {
       List<Map<String, Long>> stats = userService.countUsersByRegistrationMonth();
       return new ResponseEntity<>(stats, HttpStatus.OK);
   }

}