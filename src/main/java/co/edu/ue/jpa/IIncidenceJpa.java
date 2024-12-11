package co.edu.ue.jpa;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.ue.entity.Incidencia;


public interface IIncidenceJpa extends JpaRepository<Incidencia, Integer>  {
    List<Incidencia> findByLocalidad(String localidad);
    
    @Query("SELECT i FROM Incidencia i WHERE DATE(i.fecha) = :fecha")
    List<Incidencia> findByFecha(@Param("fecha") java.sql.Date fecha);
    
    @Query(value = "SELECT * FROM incidencias i WHERE i.categoria_id = (SELECT c.categoria_id FROM categorias_incidencias c WHERE c.nombre_categoria = :nombreCategoria)", nativeQuery = true)
    List<Incidencia> findByCategoriaNombre(@Param("nombreCategoria") String nombreCategoria);

    
    List<Incidencia> findByUsuarioId( int usuarioId);
    
    
    //Incidencias por localidad
    @Query("SELECT i.localidad AS localidad, COUNT(i) AS total FROM Incidencia i GROUP BY i.localidad")
    List<Map<String, Long>> countIncidencesByLocality();
  /*  //Incidencias por categoría
    @Query(value = "SELECT c.nombre_categoria AS categoria, COUNT(i.id) AS total " +
               "FROM incidencias i " +
               "JOIN categorias_incidencias c ON i.categoria_id = c.categoria_id " +
               "GROUP BY c.nombre_categoria", nativeQuery = true)
    List<Map<String, Long>> countIncidencesByCategory();*/
    //Incidencias por fecha
    @Query("SELECT DATE(i.fecha) AS fecha, COUNT(i) AS total FROM Incidencia i GROUP BY DATE(i.fecha)")
    List<Map<String, Long>> countIncidencesByDate();
    //Cantidad de Incidencias
    @Query("SELECT COUNT(i) FROM Incidencia i")
    Long countIncidencias();
}
