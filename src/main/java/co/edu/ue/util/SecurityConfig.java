package co.edu.ue.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    AuthenticationManager auth;

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration conf) {
        try {
            auth = conf.getAuthenticationManager();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            e.printStackTrace();
        }
        return auth;
    }

    @Bean
    public JdbcUserDetailsManager usersDetailsJdbc() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/alerta_bogota");
        ds.setUsername("root");
        ds.setPassword("");

        JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(ds);
        
        // Consulta para obtener el usuario
        jdbcDetails.setUsersByUsernameQuery(
                "SELECT u.use_email, u.use_pass, r.rol_id " +
                "FROM usuarios u " +
                "JOIN roles r ON u.rol_id = r.rol_id " +
                "WHERE u.use_email = ?"
            );
            
            // Consulta para obtener los roles del usuario
            jdbcDetails.setAuthoritiesByUsernameQuery(
            	    "SELECT r.rol_id, r.nombre_rol " +
            	    "FROM roles r " +
            	    "JOIN usuarios u ON u.rol_id = r.rol_id " +
            	    "WHERE u.use_email = ?"
            );

        return jdbcDetails;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(aut -> aut
            		//Endpoint de Usuarios 
                    .requestMatchers(HttpMethod.GET, "/api/users/all-users").hasAuthority("ADMINISTRADOR") 
                    .requestMatchers(HttpMethod.GET, "/api/users/search-user").hasAuthority("ADMINISTRADOR") 
                    .requestMatchers(HttpMethod.PUT, "/api/users/update").hasAuthority("ADMINISTRADOR") 
                    .requestMatchers(HttpMethod.DELETE, "/api/users/delete").hasAuthority("ADMINISTRADOR")
                    //Endpoint de Incidencias
                    .requestMatchers(HttpMethod.GET, "/api/incidences/listIncidences").hasAuthority("ADMINISTRADOR")
                    .requestMatchers(HttpMethod.GET, "/api/incidences/searchByLocality").hasAuthority("USUARIO")
                    .requestMatchers(HttpMethod.GET, "/api/incidences/searchByCategory").hasAuthority("USUARIO")
                    .requestMatchers(HttpMethod.GET, "/api/incidences/searchByDate").hasAuthority("USUARIO")
                    .requestMatchers(HttpMethod.POST, "/api/incidences/postIncidence").hasAuthority("USUARIO")
                    .requestMatchers(HttpMethod.DELETE, "/api/incidences/deleteIncidence").hasAuthority("USUARIO")
                    .requestMatchers(HttpMethod.PUT, "/api/incidences/updateIncidence").hasAuthority("USUARIO")
                    
                    //Demas Endpoints
                .anyRequest().permitAll() // Permitir acceso a cualquier otra solicitud
            )
            .authenticationManager(auth) // Usar el AuthenticationManager
            .addFilterBefore(new AuthorizationFilterJWT(auth), UsernamePasswordAuthenticationFilter.class); 

        return http.build();
    }
}