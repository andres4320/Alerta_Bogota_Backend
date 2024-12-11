package co.edu.ue.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.ue.entity.CategoriasIncidencia;
import co.edu.ue.entity.Incidencia;
import co.edu.ue.entity.Usuario;


public class Tools {
	
	@Autowired
	public static final String CLAVE="123456789012345678901234657980aa";
	public static final String ENCABEZADO ="Authorization";
	public static final String PREFIJO_TOKEN ="Bearer ";
	
	//Expresiones regulares para Incidencias
	public static final String RegexLocalidad = "^[\\p{L}0-9\\s]{1,100}$";
	public static final String RegexDescripcion = "^[\\p{L}\\p{N}\\s.,:\"'\\-@#*]{1,255}$";
	public static final String RegexDireccion = "^[\\p{L}0-9\\s.,-@#*]{1,255}$";
	
    public static boolean verificarExpresionesIncidencias(Incidencia incidencia) {
        if (incidencia == null) {
            return false;
        }

        boolean descripcionValida = Pattern.matches(RegexDescripcion, incidencia.getDescripcion());
        boolean direccionValida = Pattern.matches(RegexDireccion, incidencia.getDireccion());
        boolean localidadValida = Pattern.matches(RegexLocalidad, incidencia.getLocalidad());
        boolean fechaValida = incidencia.getFecha() != null;

        return descripcionValida && direccionValida && localidadValida && fechaValida;            
        
    }
    
    //Expresiones Regulares Categorias
    public static final String RegexNombre = "^[\\p{L}0-9\\s]{1,100}$"; 
    public static final String RegexDesCategoria = "^[\\p{L}\\p{N}\\s.,:\"'-]{1,255}$"; 
	public static boolean verificarExpresionesCategorias(CategoriasIncidencia categoria) {
		if (categoria == null) {
            return false;
        }

        boolean descripcionCategoriaValida = Pattern.matches(RegexDesCategoria, categoria.getDescripcionCategoria());
        boolean nombreValida = Pattern.matches(RegexNombre, categoria.getNombreCategoria());


        return descripcionCategoriaValida && nombreValida ;
	}
	
	// Expresiones regulares para Usuario
	public static final String RegexNombreApellido = "^[\\p{L}]{1,50}$";
    public static final String RegexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String RegexContrasena = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$"; // Al menos 8 caracteres, al menos una letra y un número

    public static Map<String, String> verificarExpresionesUsuario(Usuario usuario) {
        Map<String, String> errores = new HashMap<>();

        if (usuario == null) {
            errores.put("general", "El usuario no puede ser nulo.");
            return errores;
        }

        if (!Pattern.matches(RegexNombreApellido, usuario.getPrimerNombre())) {
            errores.put("primerNombre", "El primer nombre es inválido.");
        }
        
        if (!Pattern.matches(RegexNombreApellido, usuario.getPrimerNombre())) {
            errores.put("segundoNombre", "El segundo nombre es inválido.");
        }
        
        if (!Pattern.matches(RegexNombreApellido, usuario.getPrimerApellido())) {
            errores.put("primerApellido", "El primer apellido es inválido.");
        }
        
        if (!Pattern.matches(RegexNombreApellido, usuario.getPrimerApellido())) {
            errores.put("segundoApellido", "El segundo apellido es inválido.");
        }

        if (!Pattern.matches(RegexEmail, usuario.getUseEmail())) {
            errores.put("useEmail", "El correo electrónico es inválido. Debe seguir el formato correcto (ejemplo@dominio.com).");
        }

        if (!Pattern.matches(RegexContrasena, usuario.getUsePass())) {
            errores.put("usePass", "La contraseña es inválida. Debe tener al menos 8 caracteres e incluir un número.");
        }

        return errores;
    }
}

