package co.edu.ue.dao;


import java.sql.Date;
import java.util.List;


import co.edu.ue.entity.Incidencia;

public interface IIncidenceDao {
    List<Incidencia> listIncidences();
    List<Incidencia> searchByLocality(String localidad);
    List<Incidencia> searchByCategory(String nombreCategoria);
    List<Incidencia> searchByDate(Date fecha);
    boolean postIncidence(Incidencia incidencia);
    boolean updateIncidence(int id, Incidencia incidencia);
    boolean deleteIncidence(int id);


}
