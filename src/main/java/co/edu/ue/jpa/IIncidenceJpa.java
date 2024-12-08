package co.edu.ue.jpa;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ue.entity.Incidencia;


public interface IIncidenceJpa extends JpaRepository<Incidencia, Integer>  {
    List<Incidencia> findByLocalidad(String localidad);
    List<Incidencia> findByCategoriasIncidencia_nombreCategoria(String nombreCategoria);
	//List<Incidencia> findByFecha(Date fecha);
    @Query("SELECT i FROM Incidencia i WHERE DATE(i.fecha) = :fecha")
    List<Incidencia> findByFecha(@Param("fecha") java.sql.Date fecha);
    //Incidencias por localidad
    @Query("SELECT i.localidad AS localidad, COUNT(i) AS total FROM Incidencia i GROUP BY i.localidad")
    List<Map<String, Long>> countIncidencesByLocality();
    //Incidencias por categoría
    @Query("SELECT c.nombreCategoria AS categoria, COUNT(i) AS total FROM Incidencia i JOIN i.categoriasIncidencia c GROUP BY c.nombreCategoria")
    List<Map<String, Long>> countIncidencesByCategory();
    //Incidencias por fecha
    @Query("SELECT DATE(i.fecha) AS fecha, COUNT(i) AS total FROM Incidencia i GROUP BY DATE(i.fecha)")
    List<Map<String, Long>> countIncidencesByDate();
}
