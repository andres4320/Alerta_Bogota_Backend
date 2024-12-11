package co.edu.ue.service;


import java.sql.Date;
import java.util.List;
import java.util.Map;

import co.edu.ue.entity.Incidencia;

public interface IIncidenceService {    List<Incidencia> listIncidences();
	List<Incidencia> searchByLocality(String localidad);
;
	List<Incidencia> searchByDate(Date fecha);
	boolean postIncidence(Incidencia incidencia);
	boolean updateIncidence(int id, Incidencia incidencia);
	boolean deleteIncidence(int id);

	List<Incidencia> searchByUsuarioId(int usuarioId);
	List<Incidencia> searchByCategory(String nombreCategoria);
	List<Map<String, Long>> countIncidencesByLocality();
    List<Map<String, Long>> countIncidencesByCategory();
    List<Map<String, Long>> countIncidencesByDate();
}
