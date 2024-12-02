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

public class AuthorizationFilterJWT extends BasicAuthenticationFilter {

	public AuthorizationFilterJWT(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(Tools.ENCABEZADO);
        if (header == null || !header.startsWith(Tools.PREFIJO_TOKEN)) {
            chain.doFilter(request, response);
            return; 
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
    
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Tools.ENCABEZADO);

        System.out.println("Token recibido: " + token);

        if (token != null) {
            try {
                // Eliminar el prefijo del token
                String jwt = token.replace(Tools.PREFIJO_TOKEN, "");

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Tools.CLAVE.getBytes())
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                String user = claims.getSubject();
                Date expiration = claims.getExpiration();

                System.out.println("Usuario extraído del token: " + user); 
                System.out.println("Fecha de expiración del token: " + expiration);

                // Validar que el token no haya expirado
                if (expiration.before(new Date())) {
                    System.out.println("El token ha expirado.");
                    throw new JwtException("Token expirado");
                }

                // Obtener las autoridades desde el token
                List<String> authorities = (List<String>) claims.get("authorities");
                System.out.println("Autoridades extraídas del token: " + authorities);

                return new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
            } catch (JwtException e) {
                System.out.println("Error al validar el token: " + e.getMessage());
                throw new RuntimeException("Token no válido: " + e.getMessage());
            }
        }
        return null;
    }
}