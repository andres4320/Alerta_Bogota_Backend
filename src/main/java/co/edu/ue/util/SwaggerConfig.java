package co.edu.ue.util;


import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
		info=@Info(
				title = "Alerta Bogota",
				description = "Autenticacion con JWT",
				termsOfService = "Debe aceptar terminos y condiciones",
				contact = @Contact(name = "sergio.andres.garzon17@gmail.com",
				url = "www.uniempresarial.edu.co")),
		security = @SecurityRequirement(name = "Security TokenJWT"
				)
		)
@SecurityScheme(
		name = "Security TokenJWT",
		description = "Security con JWT",
		type = SecuritySchemeType.HTTP,
		paramName = HttpHeaders.AUTHORIZATION,
		in = SecuritySchemeIn.HEADER,
		scheme = "bearer", bearerFormat = "JWT")

public class SwaggerConfig {

}
