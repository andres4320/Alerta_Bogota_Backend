package co.edu.ue.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static co.edu.ue.util.Tools.*;

public class AuthorizationFilterJWT extends BasicAuthenticationFilter {

	public AuthorizationFilterJWT(AuthenticationManager authenticationManager) {
        super(authenticationManager);		
    }
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, 
            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(ENCABEZADO);
        System.out.println("Encabezado: " + header);
        if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }
        //Obtenemos los datos del usuario a partir del Token
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);	
    }

	private UsernamePasswordAuthenticationToken getAuthentication
	(HttpServletRequest request) {
	    // Revisar que el token venga en la cabecera de la petición
	    String token = request.getHeader(Tools.ENCABEZADO);

	    if (token != null) {
	        try {
	            // Procesa el token y recupera el usuario y los roles
	            Claims claims = Jwts.parserBuilder()
	                    .setSigningKey(Tools.CLAVE.getBytes())
	                    .build()
	                    .parseClaimsJws(token.replace(Tools.PREFIJO_TOKEN, ""))
	                    .getBody();

	            String user = claims.getSubject();
	            Date expiration = claims.getExpiration();

	            // Validar que el token no haya expirado
	            if (expiration.before(new Date())) {
	                throw new JwtException("Token expirado");
	            }

	            // Obtener las autoridades desde el token
	            List<String> authorities = (List<String>) claims.get("authorities");

	            // Imprimir información del usuario y roles para depuración
	            System.out.println("************** Usuario: " + user);
	            System.out.println("Autoridades: " + authorities);

	            // Crear el objeto de autenticación si el usuario es válido
	            if (user != null) {
	                return new UsernamePasswordAuthenticationToken(
	                        user, 
	                        null, 
	                        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
	                );
	            }

	        } catch (JwtException e) {
	            // Manejo de errores de token
	            throw new RuntimeException("Token no válido: " + e.getMessage());
	        }
	    }
	    return null;
	}	
}
