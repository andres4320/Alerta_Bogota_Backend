package co.edu.ue.dao;

import java.util.List;

import co.edu.ue.entity.Incidencia;

public interface IIncidenceDao {
    List<Incidencia> listarTodasIncidencias();
    List<Incidencia> buscarPorLocalidad(String localidad);
    boolean crearIncidencia(Incidencia incidencia);
    boolean actualizarIncidencia(int id, Incidencia incidencia);
    boolean eliminarIncidencia(int id);


}
