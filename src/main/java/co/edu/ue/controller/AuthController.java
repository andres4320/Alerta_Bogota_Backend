package co.edu.ue.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.util.Tools;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	AuthenticationManager authenticationManager;
	
	public AuthController(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping(value = "login", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getLogin(@RequestParam("user") String user, 
	                                       @RequestParam("pwd") String pwd) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(user, pwd));
	        
	        // Suponiendo que el método `getToken` devuelve un token como String.
	        String token = getToken(authentication);
	        return new ResponseEntity<>(token, HttpStatus.OK);
	    } catch (AuthenticationException e) {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	}
		
	private String getToken(Authentication authentication) {
		// Asegúrate de que estás obteniendo las autoridades correctas
	    List<String> authorities = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());
 
	    // Imprimir las autoridades para depuración
	    System.out.println("*****************COntroller***********************");
	    System.out.println("Autoridades: " + authorities);
	    System.out.println("****************************************");
	    String token = Jwts.builder()
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setSubject(authentication.getName())
	        .claim("authorities", authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
	        .signWith(Keys.hmacShaKeyFor(Tools.CLAVE.getBytes()))
	        .compact();
	    return token;
	}
}
