package co.edu.ue.util;

import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.ue.entity.Incidencia;


public class Tools {
	
	@Autowired
	public static final String CLAVE="123456789012345678901234657980aa";
	public static final String ENCABEZADO ="Authorization";
	public static final String PREFIJO_TOKEN ="Bearer ";
	
	//Expresiones regulares para Incidencias
    public static final String RegexLocalidad = "^[\\p{L}0-9\\s]{1,100}$"; 
    public static final String RegexDescripcion = "^[\\p{L}\\p{N}\\s.,:\"'-]{1,255}$"; 
    public static final String RegexDireccion = "^[\\p{L}0-9\\s.,-]{1,255}$"; 	
	
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
    
    //Agregar demás expresiones....
	
}

