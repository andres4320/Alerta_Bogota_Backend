package co.edu.ue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Usuario;
import co.edu.ue.service.IUserService;
import co.edu.ue.util.Tools; 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controlador para Login", description = "Este es el controlador para login utilizando JWT")
@CrossOrigin(origins = "*")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private IUserService userService;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Operation(summary = "Iniciar sesión en la Aplicacion", description = "El usuario se debe loguear para usar los métodos.")
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestParam("user") String user,
                                         @RequestParam("pwd") String pwd) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user, pwd));
            
            // Obtener el usuario autenticado
            String email = user; // Asumiendo que 'user' es el email
            Usuario usuario = userService.findByUseEmail(email);

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
            }

            // Generar el token
            String token = getToken(authentication);
            
            // Crear un objeto de respuesta que incluya el token y los datos del usuario
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", usuario); // Incluye todos los datos del usuario
            
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    
    private String getToken(Authentication authentication) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        
     // Imprimir las autoridades para depuración
        System.out.println("***************** Controller ***********************");
        System.out.println("Autoridades: " + authorities); 
        System.out.println("****************************************************");

        // Generar el token JWT
        String token = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(authentication.getName())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(Tools.CLAVE.getBytes()))
                .compact();

        return token;
    }
}