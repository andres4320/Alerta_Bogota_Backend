package co.edu.ue.dao;


import java.sql.Date;
import java.util.List;


import co.edu.ue.entity.Incidencia;

public interface IIncidenceDao {
    List<Incidencia> listarTodasIncidencias();
    List<Incidencia> buscarPorLocalidad(String localidad);
    List<Incidencia> buscarPorCategoriasIncidencia_Nombre(String nombreCategoria);
    List<Incidencia> buscarPorFecha(Date fecha);
    boolean crearIncidencia(Incidencia incidencia);
    boolean actualizarIncidencia(int id, Incidencia incidencia);
    boolean eliminarIncidencia(int id);


}
