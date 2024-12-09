package co.edu.ue.jpa;


import java.util.List;

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
    
    List<Incidencia> findByUsuario_usuarioId( int usuarioId);

}
