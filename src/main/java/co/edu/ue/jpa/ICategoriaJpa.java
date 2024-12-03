package co.edu.ue.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ue.entity.CategoriasIncidencia;

public interface ICategoriaJpa extends JpaRepository<CategoriasIncidencia, Integer> {
    List<CategoriasIncidencia> findByNombreCategoria(String nombreCategoria); 
}