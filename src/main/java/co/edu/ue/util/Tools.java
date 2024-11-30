package co.edu.ue.util;

import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.ue.entity.Course;
import co.edu.ue.entity.Incidencia;


public class Tools {
	
	@Autowired
	Course course;
	public static final String CLAVE="123333333333333aaaaaaaaa900000000";
	public static final String ENCABEZADO ="Authorization";
	public static final String PREFIJO_TOKEN ="Bearer ";
	
	//public static final String RegexCouId = "^[1-9]\\d{0,24}$";
	public static final String RegexCouDescription = "^[\\p{L}\\p{N}\\s.,:\"'-]{1,255}$";
	public static final String RegexcouName = "^[\\p{L}0-9\\s]{1,150}$";
	public static final String RegexcouPrice = "^(0|[1-9]\\d*)(\\.\\d+)?$"; 
	public static final String RegextypeId = "^[1-9]\\d{0,10}$";
	public static final String RegextypeDescription = "^[\\p{L}0-9\\s]{1,255}$";
	
	
	//Expresiones regulares para Incidencias
    public static final String RegexLocalidad = "^[\\p{L}0-9\\s]{1,100}$"; 
    public static final String RegexDescripcion = "^[\\p{L}\\p{N}\\s.,:\"'-]{1,255}$"; 
    public static final String RegexDireccion = "^[\\p{L}0-9\\s.,-]{1,255}$"; 	

	
	public static boolean verificarExpresiones(Course course) {
        if (course == null) {
            return false;
        }

        //boolean couIdValido = Pattern.matches(RegexCouId, String.valueOf(course.getCouId()));
        boolean couDescriptionValido = Pattern.matches(RegexCouDescription, course.getCouDescription());
        boolean couNameValido = Pattern.matches(RegexcouName, course.getCouName());
        boolean couPriceValido = Pattern.matches(RegexcouPrice, String.valueOf(course.getCouPrice()));
        boolean typeIdValido = Pattern.matches(RegextypeId, String.valueOf(course.getTypeCourse().getTypeId()));
        boolean typeDescriptionValido = Pattern.matches(RegextypeDescription, course.getTypeCourse().getTypeDescription());

        return couDescriptionValido && couNameValido && couPriceValido && typeIdValido && typeDescriptionValido;
    }
	
    public static boolean verificarExpresionesIncidencias(Incidencia incidencia) {
        if (incidencia == null) {
            return false;
        }


        boolean descripcionValida = Pattern.matches(RegexDescripcion, incidencia.getDescripcion());
        boolean direccionValida = Pattern.matches(RegexDireccion, incidencia.getDireccion());
        boolean localidadValida = Pattern.matches(RegexLocalidad, incidencia.getLocalidad());

        // Validar que la fecha no sea nula
        boolean fechaValida = incidencia.getFecha() != null;

        return descripcionValida && direccionValida && localidadValida && fechaValida;            }


	
}

