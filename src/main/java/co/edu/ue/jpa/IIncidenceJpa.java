package co.edu.ue.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.Incidencia;


public interface IIncidenceJpa extends JpaRepository<Incidencia, Integer>  {
    List<Incidencia> findByLocalidad(String localidad);

}
